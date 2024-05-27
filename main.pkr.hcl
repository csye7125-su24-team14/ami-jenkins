build {
  name = "build-packer"
  sources = [
    "source.amazon-ebs.my-ami"
  ]

  provisioner "shell" {
    script = "./scripts/script.sh"
  }
}

source "amazon-ebs" "my-ami" {
  ami_name        = "csye7125_${formatdate("YYYY_MM_DD_hh_mm_ss", timestamp())}"
  ami_description = " AMI for Jenkins Server"
  instance_type   = "t2.micro"
  region          = "${var.aws_region}"
//   profile         = "${var.aws_profile}"
  access_key   = "${var.aws_access_key_id}"
  secret_key   = "${var.aws_secret_access_key}"
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