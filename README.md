#Pharmacy service
My course work, developed using Spring Boot and Angular 7. 
The purpose of it is to develop a service for Pharmacy orders managing.
Planned functionality is ready.

The pharmacy has different medicines. Some of them are purchased in producers, some are produced by Pharmacy. To produce meds Pharmacy need components. There is a many-to-many relationship between meds and component tables.
The DB tables relationship is attached:
https://drive.google.com/file/d/1hzjZZID4gKCxx1DHu_ujvFrIasuOC2Vu/view?usp=sharing

Show orders tab
Shows all orders that were made. Pagination is working. Flexible UI.

Create order tab
- Add Medicine - we cannot create new medicines. We can only searcs for existing ones. Example: Nurofen.
- Add Receipt - we need just to fiil this fields, thay are uniq for each order.
- Add Doctor - we can add new Doctor. If such name and Surname already exist, system will not create a new line in the DB, it will just use the existing one.
- Add Patient - first, we should check if Patient already used our service. We can do in by phone number. If number exist - other forms will be filled automatically. If no - we need to create a new line in the DB. If we won't check the phone number and the system will detect duplicating of Name, Surname and phone number - we will get a violation message.

Information tab
- Here we can make complex queries.

We also will get messages about all our actions.


