#!/usr/bin/env bash
aws cloudformation deploy --stack-name book-management-vpc --template-file ./vpc.yaml --region us-east-1 --no-fail-on-empty-changeset
aws cloudformation deploy --stack-name book-management-security --template-file ./security.yaml --capabilities CAPABILITY_IAM --region us-east-1 --no-fail-on-empty-changeset
aws cloudformation deploy --stack-name book-management-web --template-file ./webserver.yaml --region us-east-1 --capabilities CAPABILITY_NAMED_IAM --no-fail-on-empty-changeset