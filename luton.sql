show databases;
create database mycompany;
use mycompany;

create table Customer(
CustomerID int primary key not null auto_increment ,
FirstName varchar(255) not null,
LastName varchar(255) not null,
Email varchar(255) not null,
MobileNo int(15) not null,
Address varchar(255) not null,
Payment varchar(255) not null);

show tables;
describe Customer;




