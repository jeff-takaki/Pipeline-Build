#!/usr/bin/env bash
aws cloudformation delete-stack --stack-name book-management-web 
aws cloudformation wait stack-delete-complete --stack-name book-management-web
aws cloudformation delete-stack --stack-name book-management-db
aws cloudformation wait stack-delete-complete --stack-name book-management-db
aws cloudformation delete-stack --stack-name book-management-security 
aws cloudformation wait stack-delete-complete --stack-name book-management-security