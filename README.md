# bankingtokensystem
Banking token system

Database tables:


1. Customer
id
name
phoneNumber
servideType
addressId

2. Address

id
addressLine1
addressLine2
city
state
pincode
created

3. Counter
id
number
priority
queueSize


4. Service

id
name
type
nextServiceId

5. Token
id
number
status
currentCounterId
currentServiceId
customerId
created

6. ServiceCounterMapping
id
type
counterId
serviceId

7. TokenServiceMapping

id
comments
serviceId
tokenId










