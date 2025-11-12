# Shopping_Cart — Project Report

## 1 - Introduction

The Shopping_Cart repository is a compact Java demo application that illustrates multiple classic object‑oriented design patterns and good design practices (including SOLID principles). It models products, inventory, a shopping cart with decorators (gift wrap and warranty), discount strategies, a small notification mechanism, and a facade providing a unified API. The project is intended as an educational sample and a small, extensible foundation for expansion.

## 2 - Project Overview

The application demonstrates how separate subsystems (creation, decoration, discounting, observing, and orchestration) can be combined while staying decoupled and testable. The system emphasizes clarity, openness to extension, and adherence to SOLID principles.

### 2.1 Project Structure

Root package: `com.ecommerce`

- `app`
  - `Main.java` — application entry point and demo runner
- `builder`
  - `Product` (immutable built object), `ProductBuilder`, `ProductDirector`, `LaptopConstructionStrategy`, `SmartphoneConstructionStrategy`
- `factory`
  - `Product` (interface), `ProductFactory`, `Book`, `Electronics`, `Clothing`
- `decorator`
  - `CartItem` (interface), `BaseCartItem`, `CartDecorator`, `GiftWrapDecorator`, `WarrantyDecorator`
- `cart`
  - `ShoppingCart` — holds `CartItem`s and uses a `DiscountStrategy`
- `strategy`
  - `DiscountStrategy`, `NoDiscount`, `FlatDiscount`, `PercentageDiscount`
- `observer`
  - `Subject`, `Observer`, `Inventory`, `ProductInventory`, `StockUpdate`, `StockUpdateNotifier`, `PriceUpdate`, `PriceUpdateNotifier`, `Customer`, `CustomerNotificationObserver`
- `facade`
  - `ECommerceFacade` — high-level API that coordinates subsystems

Build tool: Maven (`pom.xml`).

---

## 3 - Implemented Design Patterns

For each pattern below: intent and an example from the codebase.

### 3.1 Builder Pattern
- Intent: Separate the construction of a complex object from its representation. Provide a clear, validated construction path for immutable objects.
- Example: `com.ecommerce.builder.Product` is constructed via `Product.ProductBuilder`. `ProductDirector` uses `ProductConstructionStrategy` implementations (`LaptopConstructionStrategy`, `SmartphoneConstructionStrategy`) to create `Product` instances.

### 3.2 Factory Pattern
- Intent: Encapsulate object creation so clients depend on an abstract product interface rather than concrete classes.
- Example: `com.ecommerce.factory.ProductFactory` exposes `createProduct(type, name, price)` and returns `Product` instances (concrete classes: `Book`, `Electronics`, `Clothing`). The factory supports registering new creators to add types without changing the factory code.

### 3.3 Decorator Pattern
- Intent: Add responsibilities to objects dynamically and transparently by wrapping them.
- Example: `CartItem` is the component interface. `BaseCartItem` is the core implementation. `CartDecorator` is the abstract decorator that wraps a `CartItem`. `GiftWrapDecorator` and `WarrantyDecorator` add cost and descriptive information. `ECommerceFacade` composes these decorators before adding the item to the `ShoppingCart`.

### 3.4 Facade Pattern
- Intent: Provide a simple interface to a set of subsystems, making them easier for clients to use.
- Example: `ECommerceFacade` aggregates `ProductDirector`, `ProductFactory`, `ProductInventory`, and `ShoppingCart` and exposes actions like `addProductToCart(...)`, `setDiscountStrategy(...)`, `subscribeCustomer(...)`, `updateInventory(...)`, and `updatePrice(...)`.

### 3.5 Observer Pattern
- Intent: Define a subscription mechanism to notify multiple observers about state changes in a subject.
- Example: `Inventory` implements `Subject` and notifies general `Observer`s. `ProductInventory` uses specialized interfaces `StockUpdate` and `PriceUpdate`; `StockUpdateNotifier` and `PriceUpdateNotifier` wrap `Customer` instances to send notifications upon stock or price changes.

### 3.6 Strategy Pattern
- Intent: Encapsulate interchangeable algorithms (strategies) and make them pluggable at runtime.
- Example: `DiscountStrategy` is the interface. Implementations include `NoDiscount`, `FlatDiscount`, and `PercentageDiscount`. `ShoppingCart` holds a `DiscountStrategy` and uses it to calculate the final amount.

## 4 - SOLID Principles

This project follows the SOLID principles. Below are short definitions and concrete mappings to the codebase.

- Single Responsibility Principle (SRP)
  - A class should have only one reason to change.
  - Examples: product classes (`Book`, `Electronics`, `Clothing`) model product data only; `ShoppingCart` manages items and totals; `ECommerceFacade` handles orchestration only.

- Open/Closed Principle (OCP)
  - Software entities should be open for extension, but closed for modification.
  - Examples: `ProductFactory` allows registering new product creators; `ProductDirector` accepts new `ProductConstructionStrategy` implementations; decorators add behaviour without changing `BaseCartItem`.

- Liskov Substitution Principle (LSP)
  - Subtypes must be substitutable for their base types.
  - Examples: `Book`, `Electronics`, and `Clothing` implement the `com.ecommerce.factory.Product` interface and can be used wherever `Product` is expected. `CartDecorator` subclasses are substitutable for `CartItem`.

- Interface Segregation Principle (ISP)
  - Prefer many small, specific interfaces over a large one.
  - Examples: `DiscountStrategy`, `CartItem`, `ProductConstructionStrategy`, `StockUpdate`, `PriceUpdate`, `CustomerNotificationObserver` keep contracts focused and minimal.

- Dependency Inversion Principle (DIP)
  - High-level modules should depend on abstractions rather than concretions.
  - Examples: `ShoppingCart` depends on `DiscountStrategy` rather than concrete discount classes. `ECommerceFacade` accepts its dependencies (director, factory, inventory, cart) and can be given test doubles.

Concrete usage snippets are provided in the Appendix.

## 5 - Execution Example

### How to build & run (PowerShell on Windows)

1. Build with Maven:

```powershell
mvn -q clean package
```

2. Run the demo using the Maven exec plugin:

```powershell
mvn -q exec:java -Dexec.mainClass="com.ecommerce.app.Main"
```

Or after packaging, run the main class directly (adjust classpath as needed):

```powershell
java -cp target\\classes;target\\dependency\\* com.ecommerce.app.Main
```

### Program Output (representative)

```
=== E-Commerce System Demo === 

 Builder Pattern Demo - Created: Product{name='Smartphone', color='Black', size='6.1 inch', storage=128, price=999.99} 

Factory Pattern Demo - Created: Electronics: Gaming Laptop 

Notification for John Doe: Gaming Laptop - Low stock alert: 3 units remaining 

Decorator Pattern Demo - Cart Items: 

- Gaming Laptop + Gift Wrap + Extended Warranty ($1334.98) 

Total Cost: $1334.98 

  

=== Adding Discount Strategy === 

Total after 10% discount: $1201.482 
```


## 6 - Conclusion

Shopping_Cart is intentionally compact and instructive. It demonstrates how well-known design patterns can be combined to create modular, extensible systems. The codebase emphasizes separation of concerns, use of abstractions, and runtime composition through patterns like Strategy and Decorator.

## 7 - Further Work

- Persist inventory and orders (JPA, H2 or Postgres).
- Replace console notifications with a pluggable `NotificationService` and adapters for email/SMS.
- Add unit and integration tests (JUnit) and a CI pipeline (GitHub Actions).
- Add a REST API layer (Spring Boot) exposing the `ECommerceFacade` for real clients.
- Extract inventory/notification as a Go microservice and demonstrate HTTP interop.
- Render PlantUML diagrams into `docs/uml/` and add console screenshots to `docs/screenshots/`.

---

