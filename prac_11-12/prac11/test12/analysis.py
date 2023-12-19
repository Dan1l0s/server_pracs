from .models import Prac
import zipfile
import matplotlib.pyplot as plt
import numpy as np
from PIL import Image
import os
from django.core.files import File
import requests


def showFiles(id):
    prac = Prac.objects.get(id=id)
    f = zipfile.ZipFile(prac.files)
    return f.namelist()

def makeNotZipAnalysis(id):

    url = f'http://gogogo:8080/files'
    r = requests.get(url)
    data = r.json()

    x = np.array([])
    y = np.array([])

    languages = {}

    for elem in data:
        name = elem["Name"]

        if name.rfind('.')!=-1:
            lan = name[name.rfind('.'):]
        else:
            lan = "None"
        if not lan in languages.keys():
            languages[lan]=1
        else:
            languages[lan]+=1

        temp = 1
        if name in x:
            while '{} ({})'.format(name, temp) in x:
                temp += 1
            name += ' ({})'.format(temp)
        x = np.append(x, name)
        y = np.append(y, elem["Length"]/1024)
        

    fig = plt.figure(figsize =(10, 12))
    fig.suptitle('График размера файла')
    x2,y2 = zip(*sorted(zip(x,y),key=lambda x: x[0]))
    plt.plot(x2, y2)
    plt.xlabel('Имя файла')
    plt.ylabel('Размер файла, мб')
    plt.xticks(rotation=90)
    plt.subplots_adjust(bottom=0.3)
    plt.savefig('graph')

    data1 = languages.keys()
    data2 = languages.values()
    fig = plt.figure(figsize =(10, 7))
    fig.suptitle('Диаграмма соотношения количества файлов по расширению')
    plt.pie(data2, labels=data1)
    plt.savefig('diagramm')

    img1 = Image.open('graph.png')
    img2 = Image.open('diagramm.png')
    img = Image.new("RGB", (img1.size[0], img1.size[1]+img2.size[1]), "white")
    img.paste(img1, (0,0))
    img.paste(img2, (0,img1.size[1]))
    img.save('final.png')

    prac = Prac.objects.get(id=id)
    prac.img.save(
        os.path.basename('final.png'),
        File(open('final.png', 'rb'))
    )
    prac.save()




def makeZipAnalysis(id, filename):
    f = zipfile.ZipFile(filename)

    namelist = f.namelist()

    x = np.array([])
    y = np.array([])
    shotrnames = []

    languages = {}

    for elem in namelist:
        shotrnames.append(elem[elem.rfind('/')+1:])

    for name in namelist:
        if name[-1]!='/':
            if name.find('.')!=-1:
                    lan = name[name.rfind('.'):]
            else:
                lan = 'none'
            if lan not in languages:
                languages[lan] = 1
            else:
                languages[lan] += 1
            try:
                fileStr = f.read(name).decode('utf-8')
                numOfRows = fileStr.count('\n')+1
                if (shotrnames.count(name[name.rfind('/')+1:])>1):
                    x = np.append(x, name)
                else:
                    x = np.append(x, name[name.rfind('/')+1:])
                y = np.append(y, numOfRows)
            except Exception as e:
                a=1

    fig = plt.figure(figsize =(10, 12))
    fig.suptitle('Анализ архива {}\nГрафик количества строк в файле'.format(filename))
    x2,y2 = zip(*sorted(zip(x,y),key=lambda x: x[0]))
    plt.plot(x2, y2)
    plt.xlabel('Имя файла')
    plt.ylabel('Количество строк в файле')
    plt.xticks(rotation=90)
    plt.subplots_adjust(bottom=0.3)
    plt.savefig('graph')

    data1 = languages.keys()
    data2 = languages.values()
    fig = plt.figure(figsize =(10, 7))
    fig.suptitle('Диаграмма соотношения количества файлов по расширению')
    plt.pie(data2, labels=data1)
    plt.savefig('diagramm')

    img1 = Image.open('graph.png')
    img2 = Image.open('diagramm.png')


    img = Image.new("RGB", (img1.size[0], img1.size[1]+img2.size[1]), "white")
    img.paste(img1, (0,0))
    img.paste(img2, (0,img1.size[1]))

    img.save('final.png')

    

    prac = Prac.objects.get(id=id)
    prac.img.save(
        os.path.basename('final.png'),
        File(open('final.png', 'rb'))
    )
    prac.save()

