from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.decorators import api_view
import json

# Create your views here.

class HelloWorldView(APIView):

    def get(self, request):
        return Response("Hello, world!")

    def post(self, request):
        response = ''
        try:
            body_unicode = request.body.decode('utf-8')
            body_data = json.loads(body_unicode)
            name = body_data['name']
            if name!="":
                response = 'Hello, {}!'.format(name)
            else:
                response = 'Please, enter your name'
        except Exception as e:
            response = 'Error'
            print("Error with parsing body", end="\n\t")  
        return Response(response)  
    
@api_view(('GET',))
def HelloWorldFromEndpoint(request, name):
    if not name:
        return Response("Provide a name or use another endpoint")
    if name=='dan1l0s':
        return Response(f"Hello, {name}!")
    return Response("Incorrect name :/")

@api_view(('GET',))
def EmptyView(request):
    return Response("Provide a name or use another endpoint")