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


variable "aws_access_key_id" {
  type    = string
  default = env("AWS_ACCESS_KEY_ID")
}

variable "aws_secret_access_key" {
  type    = string
  default = env("AWS_SECRET_ACCESS_KEY")
}

variable "domain" {
  type    = string
  default = env("DOMAIN")
}

build {
  name = "build-packer"
  sources = [
    "source.amazon-ebs.my-ami"
  ]


  provisioner "file" {
    source      = "admin-user.groovy"
    destination = "admin-user.groovy"
  }

  provisioner "file" {
    source      = "plugins.groovy"
    destination = "plugins.groovy"
  }


  provisioner "shell" {
    environment_vars = [
      "DOMAIN=${var.domain}"
    ]
    script = "./scripts/script.sh"
  }
}

source "amazon-ebs" "my-ami" {
  // profile         = "${var.aws_profile}"
  ami_name        = "csye7125_${formatdate("YYYY_MM_DD_hh_mm_ss", timestamp())}"
  ami_description = " AMI for Jenkins Server"
  instance_type   = "t2.micro"
  region          = "${var.aws_region}"
  access_key      = "${var.aws_access_key_id}"
  secret_key      = "${var.aws_secret_access_key}"
  ssh_username    = "${var.ssh_username}"
  source_ami      = "${var.source_ami}"
  ami_regions     = ["${var.aws_region}"]

  ami_block_device_mappings {
    delete_on_termination = true
    device_name           = "/dev/sda1"
    volume_size           = 8
    volume_type           = "gp3"
  }

  aws_polling {
    delay_seconds = 120
    max_attempts  = 5
  }
}
