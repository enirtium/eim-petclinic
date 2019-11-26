#AWS Initialization
import boto3


VPC_NAME='eim-vpc'
VPC_CIDR='172.16.0.0/16'
IGW_ID='eim-igw'




# create VPC
def createVPC(ec2, name,cidr): 
  vpc = ec2.create_vpc(CidrBlock=cidr)
  # assign a name to our VPC
  vpc.create_tags(Tags=[{"Key": "Name", "Value": name}])
  vpc.wait_until_available()
  return vpc

# enable public dns hostname so that we can SSH into it later
def enableDNSForVPC(ec2Client, vpc): 
  ec2Client.modify_vpc_attribute( VpcId = vpc.id , EnableDnsSupport = { 'Value': True } )
  ec2Client.modify_vpc_attribute( VpcId = vpc.id , EnableDnsHostnames = { 'Value': True } )

# create a route table and a public route
def createRoutetable(vpc):
  routetable = vpc.create_route_table()
  return routetable


# create an internet gateway, attach to VPC and associate it to Route table
def createIGW(ec2, vpc, routetable, name):
  internetgateway = ec2.create_internet_gateway()
  internetgateway.create_tags(Tags=[{"Key": "Name", "Value": name}])
  vpc.attach_internet_gateway(InternetGatewayId=internetgateway.id)
  route = routetable.create_route(DestinationCidrBlock='0.0.0.0/0', GatewayId=internetgateway.id)
  return internetgateway

# create subnet and associate it with route table
def createSubnet(ec2, routetable, vpcId, cidr, az='us-east-1a' ):
  subnet = ec2.create_subnet(CidrBlock=cidr, VpcId=vpcId, AvailabilityZone=az)
  routetable.associate_with_subnet(SubnetId=subnet.id)



# VPC 
# Subnets
# Security Groups
# Network ACLs
# Internet Gateways
# Egress Only Internet Gateways
# Route Tables
# Network Interfaces
# Peering Connections
# Endpoints

def initNewVPC(ec2, ec2Client):
  vpc = createVPC(ec2, VPC_NAME, VPC_CIDR)
  enableDNSForVPC(ec2Client,vpc)
  rt = createRoutetable(vpc)
  igw = createIGW(ec2, vpc, rt, IGW_ID)
  subnet = createSubnet(ec2, rt, vpc.id, '172.16.1.0/24',  'us-east-1a')

  # Create a security group and allow SSH inbound rule through the VPC
  securitygroup = ec2.create_security_group(GroupName='SSH-ONLY', Description='only allow SSH traffic', VpcId=vpc.id)
  securitygroup.authorize_ingress(CidrIp='0.0.0.0/0', IpProtocol='tcp', FromPort=22, ToPort=22)


def main():
	ec2 = boto3.resource('ec2')
	ec2Client = boto3.client('ec2')

	initNewVPC(ec2, ec2Client)

	

	
	
# https://blog.ipswitch.com/how-to-create-and-configure-an-aws-vpc-with-python
if __name__ == "__main__":
    main()