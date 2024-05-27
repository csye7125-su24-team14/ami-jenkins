variable "aws_region" {
  type    = string
  default = "us-east-1"
}

variable "aws_profile" {
  type    = string
  default = "infra"
}

variable "source_ami" {
  type    = string
  default = "ami-04b70fa74e45c3917"
}

variable "ssh_username" {
  type    = string
  default = "ubuntu"
}

variable "aws_access_key_id" {
  type    = string
  default = env("AWS_ACCESS_KEY_ID")
}

variable "aws_secret_access_key" {
  type    = string
  default = env("AWS_SECRET_ACCESS_KEY")
}