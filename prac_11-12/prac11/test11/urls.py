from django.urls import path

from . import views

urlpatterns = [
    path('hello', views.HelloWorldView.as_view()),
    path('<str:name>', views.HelloWorldFromEndpoint),
    path('', views.EmptyView),
]