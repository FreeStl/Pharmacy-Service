#Pharmacy service
My course work, developed using Spring Boot and Angular 7. It is a REST application.
The purpose of it is to develop a service for Pharmacy orders managing.
Planned functionality is ready.

The Pharmacy has different medicines. They have different types (pills, liquid) and classes (purchased or produced). Some of them are purchased in producers, some are produced by Pharmacy. To produce meds Pharmacy needs components. There is a many-to-many relationship between meds and component DB tables. Cashier creates an order which will be received by the production manager. The app is designed to reduce redundant data. The explanation is below. There are also 9 unique queries we can use to check the status of Pharmacy business.
The DB tables relationship is attached:
https://drive.google.com/file/d/1hzjZZID4gKCxx1DHu_ujvFrIasuOC2Vu/view?usp=sharing

Show orders tab
- Shows all orders that were made. Pagination is working. Flexible UI.

Create order tab
- Add Medicine - we cannot create new medicines. We can only searcs for existing ones. Example: Nurofen.
- Add Receipt - we need just to fiil this fields, thay are uniq for each order.
- Add Doctor - we can add new Doctor. If such name and Surname already exist, system will not create a new line in the DB, it will just use the existing one.
- Add Patient - first, we should check if Patient already used our service. We can do in by phone number. If number exist - other forms will be filled automatically. If no - we need to create a new line in the DB. If we won't check the phone number and the system will detect duplicating of Name, Surname and phone number - we will get a violation message.

Information tab
- Here we can make complex queries.

We also will get messages about all our actions.

Back-end features:
- Almost RESTfull app. Delete function was not implemented in this app yet. Please check my RESTfull aplication https://github.com/FreeStl/Angular-practice.
- MVC.
- Created custom exeption if DB is not working. https://github.com/FreeStl/Pharmacy-Service/tree/master/src/main/java/com/nazaruk/medApteka/exeption
- DB fields 'Create Date' and 'Update date' are auto-populating. Functionality developed in class AuditModel  and is inherited by other classes-tables. https://github.com/FreeStl/Pharmacy-Service/tree/master/src/main/java/com/nazaruk/medApteka/model
- Enums are used, where it is possible, for clarity
- used java .util.Optional to handle null references.
- created custom queries. https://github.com/FreeStl/Pharmacy-Service/tree/master/src/main/java/com/nazaruk/medApteka/repository.

Front-End features:
- MVC.
- Notification system.
  Service: https://github.com/FreeStl/Pharmacy-Service/blob/master/angular-frontend/src/app/service/MessageAndError.ts
  View: https://github.com/FreeStl/Pharmacy-Service/tree/master/angular-frontend/src/app/component/messages
- Bootstrap and RxJS library. Flexible UI.
- OOP is used where is reasonable to use it. For example, all services inherit message.service.ts.
- GET, PUT, POST queries.
- Routing
- error handling system.
