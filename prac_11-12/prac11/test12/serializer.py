from rest_framework import serializers
from .models import Prac


# class PracSerializerInput(serializers.ModelSerializer):
#     class Meta:
#         model = Prac
#         fields = ["id", "files"]


class PracSerializerOutput(serializers.ModelSerializer):
    class Meta:
        model = Prac
        fields = ["id", "img"]
