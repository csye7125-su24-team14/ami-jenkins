aws_region            = "us-east-1"
aws_profile           = "infra"
source_ami            = "ami-04b70fa74e45c3917"
ssh_username          = "ubuntu"
aws_access_key_id     = env("AWS_ACCESS_KEY_ID")
aws_secret_access_key = env("AWS_SECRET_ACCESS_KEY")