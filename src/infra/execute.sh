#! /bin/bash

kubectl apply -f app-secrets.yaml
kubectl apply -f app-service.yaml
kubectl apply -f app-deployment.yaml

kubectl apply -f pg-secrets.yaml
kubectl apply -f pg-configmap.yaml
kubectl apply -f pg-service.yaml
kubectl apply -f pg-stateful.yaml

minikube service lawoffice