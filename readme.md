# PumpITup Test Assignment

## Author: Andrei Temnikov temnikov.andrey1@gmail.com

## Task

*Backend Java developer exercise*

An e-shop wants to tack items in-stock. Each item type has a category, a name and a set of configuration attributes. For
example T-Shirts have size and colour while socks have season. Each item has a reference to the item type and a set of
actual configuration values. All configuration attributes are optional. An order contains the reference to an item type
and the requested configuration. The requested configuration can be just a subset of possible configuration attributes,
that means the clients may specify only some of the configuration attributes in which case they do not care about the
other ones. Once an order is received, the goal is to get the ID of the item to be shipped and in case such item is not
in stock, to order one to the supplier.

Write a set of Java classes that would model the situation with following classes covered: category, item type, item,
order. Also implement a method that will return the item in stock that matches the order or null. Make assumptions
regarding probable ordinality of each class and adapt the model to that with outlook of system scalability and
maintainability.

Prepare a presentation for the team to discuss this including alternatives if any. The presentation can be 5-10 minutes
explanation followed up with a discussion, you may choose any way how to do the explanation.

## Solution

TL;DR: run `mvn test` to verify the solution with unit tests.

The application is maven-based library that contains a set of services to emulate an e-shop. The domain model is in
the "model" package.

Each model class is a record class that can be serialized as JSON or stored in the database.

They use *UUID* for identification. This approach allows to store the instances in a normalized database without
additional entities. Also, ORM can be utilized without the changes in the model.

*OrderRequestParams* is a model for the request, that can be parsed from a JSON. It contains the required *ItemType* and
a set of attributes for the search.

Data access layer is provided as set of dummy in-memory implementations that contain only the operations required for
the task. The exact interface of the DAO layer depends on the use cases of the applications. They can be populated for
the test purposes.

Service layer encapsulates the logic of the solution. The classes inside are provided with descriptions as Javadocs.
Currently, they are built with dynamic instantiation, but intended to use with Spring or other DI framework.

*AttributeService* applies the validation of attributes.

*ItemService* contains the logic to find the items by *OrderRequestParams* and validates params. Although the task was
to return only one ID of the item or null if not found, that requirement brings some edge-cases, like:

- Some requests may match more than one item.
- Returning only ID will require additional call to the DAO level on the controller layer.
- Semantically the number of items in stock should be returned as a part of the response.

Considering this, I decided to return a map of items and their number in stock.

*ItemTypeService* allows to find the *ItemType* by its name, provided with request parameters.

*OrderAutomationService* contains the main logic to resolve the requests. If the items that matched the request are out
of stock, the service sends the requests to the suppliers for purchasing the new ones. Although the logic is made
according to the task, the correct use case will be to ask for a confirmation from the authorized user to avoid abuse of
the system.

*StockService* allows to determine the number of items in stock.

*SupplierPurchaseService* is a dummy implementation of the service that sends the request to the item supplier. The
actual logic would require additional table with the supplier contacts, access to payment gateway, authentication and
many additional checks.

The unit tests are provided for each service. You can run them one by one or with maven using `mvn test`.

*TextFixture* contains a predefined set of data to be populated to the DAO for testing purposes. 


