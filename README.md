# ami-jenkins

## Overview

This repository contains Packer configurations to build a custom Jenkins AMI (Amazon Machine Image). The AMI is pre-configured with:

- Jenkins installation
- Initialization scripts & Required packages
- Pre-loaded Jenkins jobs
- User and password setup

## Prerequisites

- [Packer](https://www.packer.io/downloads) (version 1.7.0 or later)
- AWS CLI configured with appropriate credentials
- Basic knowledge of Packer and AWS

## Repository Structure

.
├── LICENSE
├── README.md
├── main.pkr.hcl
└── scripts
    ├── jenkins_init/
    └── script.sh


## Building the AMI

To build the Jenkins AMI, follow these steps:

1. Clone this repository
   
2. Review and modify the `main.pkr.hcl` file if needed.

3. Run the Packer build command:
    
    `packer build main.pkr.hcl`
   
This command will start the build process and create the AMI in your specified AWS region.

4. Once the build is complete, Packer will output the ID of the newly created AMI.

## Packer Validate Command

  `packer validate main.pkr.hcl`
