from django.db import models
import os

# Create your models here.

class Prac(models.Model):

    id = models.CharField(max_length=100, primary_key=True)
    img = models.ImageField(upload_to='images/')

    def __str__(self):
        return self.id

