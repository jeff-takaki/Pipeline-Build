#!/usr/bin/env bash
aws cloudformation deploy --stack-name book-management-vpc --template-file ./vpc.yaml --region us-east-1 --no-fail-on-empty-changeset
aws cloudformation deploy --stack-name book-management-ip --template-file ./ip.yaml --region us-east-1 --no-fail-on-empty-changeset
aws cloudformation deploy --stack-name book-management-security --template-file ./security.yaml --capabilities CAPABILITY_IAM --region us-east-1 --no-fail-on-empty-changeset
#aws cloudformation deploy --stack-name book-management-web-dev --template-file ./webserver-bg.yaml --region us-east-1 --capabilities CAPABILITY_NAMED_IAM --no-fail-on-empty-changeset
#aws cloudformation deploy --stack-name book-management-db --template-file ./db.yaml --region us-east-1 --capabilities CAPABILITY_NAMED_IAM --no-fail-on-empty-changeset
aws cloudformation deploy --stack-name book-management-container-dev --template-file ./container.yaml --region us-east-1 --capabilities CAPABILITY_NAMED_IAM --no-fail-on-empty-changeset
