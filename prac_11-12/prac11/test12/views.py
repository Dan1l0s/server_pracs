from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.views import APIView
from .models import Prac
from .analysis import makeZipAnalysis, makeNotZipAnalysis
from rest_framework.decorators import api_view
import wget
import requests
from .serializer import PracSerializerOutput
import os

# Create your views here.


@api_view(('GET',))
def analysis(request, id):
    url = f'http://gogogo:8080/files'
    r = requests.get(url)
    data = r.json()
    response = []
    for elem in data:
        response.append(elem["Id"])
    if id in response:
        try:
            prac = Prac.objects.filter(id=id)   
            if (not prac):
                Prac.objects.create(id=id)
            prac = Prac.objects.get(id=id)

            template_path = 'analysis.html'

            if prac.img=="":
            
                url = f'http://gogogo:8080/files/{id}/download'; 
                filename = wget.download(url, '.')

                if filename[filename.rfind('.'):]!='.zip':
                    makeNotZipAnalysis(id)
                else:
                    makeZipAnalysis(id, filename)

                prac = Prac.objects.get(id=id)
                os.remove(filename)

            context = {"image": [prac.img.url]}
            return render(request, template_path, context)
        except Exception as e:
            print(e)
            return Response("Error")
    return Response("No such id")
    

@api_view(('GET',))
def getNames(request):
    try:
        url = f'http://gogogo:8080/files'
        r = requests.get(url)
        data = r.json()
        response = []
        for elem in data:
            response.append({"id": elem["Id"], "filename":elem["Name"]})
        return Response(response)
    except Exception as e:
        print(e)
        return Response("Error")

@api_view(('DELETE',))
def deleteName(request, id):
    try:
        Prac.objects.get(id=id).delete()
        return Response("Deleted")
    except Exception as e:
        print(e)
        return Response("Error")


@api_view(('GET',)) 
def getPracs(request):
    pracs = Prac.objects
    serializer = PracSerializerOutput(pracs, many=True)
    return Response(serializer.data)





# class PracView(APIView):

#     def get(self, request):
#         pracs = Prac.objects
#         serializer = PracSerializerOutput(pracs, many=True)
#         return Response(serializer.data)

    
#     def post(self, request):
#         data = {
#             'id': request.data.get('id'), 
#             'files': request.data.get('files'), 
#         }

#         serializer = PracSerializerInput(data=data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response('Saved')
#         else:
#             print("Error with saving data", end='\n\t')
#             return Response('Error')
        


# class PracViewOperations(APIView):

#     http_method_names = ['delete', 'get', 'put']

#     def get(self, request, id):
#         try:
#             prac = Prac.objects.get(id=id)
#             serializer = PracSerializerOutput(prac)
#             return Response(serializer.data)
#         except Exception as e:
#             print(e)
#             return Response("Error")
    
#     def delete(self, request, id):
#         try:
#             Prac.objects.get(id=id).delete()
#             return Response("Deleted")
#         except Exception as e:
#             print(e)
#             return Response("Error")
    
#     def put(self, request, id):
#         data = {
#             'id': request.data.get('id'), 
#             'files': request.data.get('files'), 
#         }

#         if (data['id']!=id):
#             return Response("You entered wrong id")
#         else:
#             prac = Prac.objects.get(id=id)
#             prac.delete()
#             serializer = PracSerializerInput(data=data)
#             if serializer.is_valid():
#                 serializer.save()
#                 return Response('Updated')
#             else:
#                 print("Error with saving data")
#                 return Response('Error')
