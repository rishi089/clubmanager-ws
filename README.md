# clubmanager-ws
Class Management for Clubs/Gyms

1. Run the spring boot application directly after executing this command : mvn clean install.
Kindly note server port had been changed to 8081(in application.properties).
2. Application endpoints have been exposed in Swagger UI. Hence, for visualizing endpoints refer this:
http://localhost:8081/swagger-ui/index.html#/
3. Tried to include all the validations asked in the story.
4. Used in-memory Array.


Want to give some points on API Endpoints
1. POST /api/classes
   Request Body format used:
   {
   "name": "Pilates",
   "startDate": "2025-02-01",
   "endDate": "2025-02-20",
   "startTime": "14:00",
   "duration": 60,
   "capacity": 10
   }
2. GET /api/classes/{id}
   Created extra endpoint to fetch class details from provided id.
   
3. POST /api/bookings
   Request Body format used:
   {
   "memberName": "Ram,
   "classId": 1,
   "participationDate": "2025-02-01"
   }
4. GET /api/bookings
   You can search based on membername or date range



