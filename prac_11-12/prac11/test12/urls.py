from django.urls import path
from django.conf import settings
from django.conf.urls.static import static

from . import views

urlpatterns = [
    path('names', views.getNames),
    path('db/<str:id>', views.deleteName),
    path('analysis/<str:id>', views.analysis),
    path('db', views.getPracs)
]