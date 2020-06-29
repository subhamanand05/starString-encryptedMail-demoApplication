# starString-encryptedMail-demoApplication
This repository contains source code for following project:
1. Application that returns a string of stars (asteriks) 2^n long
2. A Spring boot project which sends encrypted email using Java.
Software Used are as below:
a) Java 1.8
b) Spring Boot 2.2.5.RELEASE
c) Inellij IDEA
d) Bouncy Castle Java Library
e) Fake SMTP

Note:
Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 8 was downloaded from below URL was used to replace policy jars under "C:\Program Files\Java\<jdk_version>\jre\lib\security\policy\unlimited"
https://www.oracle.com/java/technologies/javase-jce-all-downloads.html

The above policy files helped in resolving invalid jey size error for AES128_CBC ASN1ObjectIdentifier.