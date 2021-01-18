# SDF (Software Development Fundamentals) Implementation of the Designed Online Shopping System

Check out this project online at [https://projekt-plutus.herokuapp.com/](https://projekt-plutus.herokuapp.com/)

## Team Members

- Sanobar Shamim
- Saroja
- Satyajit Ghana

## Installation

### Clone repository

```bash
git clone https://github.com/satyajitghana/ProjektPlutus
```

### Install Angular-Cli globally

```bash
npm install -g @angular/cli
```

### Install NPM packages

```bash
cd ProjektPlutus
npm install
```

### Run development server

```bash
ng serve
```

Runs a webpack-development server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

### Run unit tests

```bash
ng test
```

Executes the unit tests via [Karma](https://karma-runner.github.io).

### Running end-to-end tests

```bash
ng e2e
```

Executes the end-to-end tests via [Protractor](http://www.protractortest.org/).

### Build app for prod

```bash
ng build --prod --build-optimizer
```

This builds the app for prod environment into a /dist folder. Uses the Angular-AOT-mode to precompile the the app. This reduces the app-footstep (compiler is around 1/3 of bundle).

## Background

The initial idea for this project was to implement a SPA based shop frontend which can be coupled to existing shop backends via REST API. The performance of a SPA application would be very nice to make the shopping process for users as fluid as possible. Imagine adding items to the cart, browsing and filtering products, typeahead search, updating your favorites/wish list - all handled by the front end application.

Although this would have been cool, it's most likely a bit out of scope to provide all the API mappings for different shops like Shopify, Woocommerce, PrestaShop, Magento and the likes.

So we decided to implement basic shop functionality for now and base the project target more around finding conclusions for challenges we will face during the process.

## Feature Set

### Shop functionality

#### Shop Front Page

- Featured Products with link to corresponding product detail page
- Features for «New Arrivals», «On Sale» and «Best Rated» Products

#### Products

- Products fetched from FireBase backend and cached for future requests
- Sorting: Products can be sorted by date created, price and name
- Products can be viewed in a grid or a list view
- Products are shown paged via a PagingService
- When logged in as a user with adminstrative rights, additional buttons are shown for product CRUD operations

#### Product CRUD

- Add a new product
- Edit existing product
- Delete existing product
- Images handled with Firebase Storage

#### Cart

- Products can be added from the list/grid View or product detail view
- Cart is handled via a CartService
- Adding the same product multiple times, increases the amount in cart
- Cart has a dedicated cart page and is visible as a dropdown widget as well
- Quantity of each cart item can be adjusted via cart page
- Cart can be cleared at once
- Single products can be removed from cart
- Subtotal and Totals will be calculated on the fly
- Link to Checkout is available from both carts

#### Checkout

- Prefill fields, if user is already logged in
- Enter Address, Shipping Method and Payment Data with Validation
- Show review of the order before final submit
- When submitting a order, OrderService creates a new Order linked to the user
- Anonymous Orders are possible too, in that case OrderService creates a new anonymous order
- Order summary is shown in the sidebar during the checkout process

#### Authentication

- Checkout: As registered user / guest
- Sign up: Create user account
- Log in: General login or during checkout
- Role based authentication

#### Account

- Create new shop user accounts
- Login with existing user account
- User Profile, Email, Password, Firstname, Lastname are updateable via account page
- Order history is visible to logged in users
- Role base authentication via Firebase, roles can be assigned to users like isAdmin

#### Orders

- Checkout process generates Order for registered user or guest
- Order / Confirmation Email for Shop/User/Guest
- Orders can be viewed by logged in user

### Security

- FireBase Security Rules for Shop User / Admin

#### General

- All displayed prices are handled via a PriceComponent to simplify currency display/formatting
- Search with Typeahead functionality (Unfortunately, FireBase has very limited  functionality for full text search. For a real application, local search would be the better option)

## Possible future features and updates

- Speed up initial page load with server rendered start page and/or lazy load modules
- SEO, was out of scope as it invloves server side rendering with Angular Universal
- Product categories
- Product stock amounts
- UI for editing Featured Products-slider
- UI for editing banner on start page
- UI for editing users/user-roles (admin only) instead of Firebase-only (i. e. User CRUD)
- Save cart for logged in user
- Login option during checkout
- Customer Address or multiple delivery addresses could be handled via account profile page
- Attach real payment gateways
- Multilanguage functionality (i18n)
- Order Detail View for logged in users via account/orders
- Add password recovery functionality for lost user passwords
- Better solution for responsive tables
- Lazy load some modules that are not needed on inital page load
- Separate routes to modules
- Multiple Product Images CRUD
- Implementing a state management (i. e. ngrx)
- Social sharing functionality
- Authenticate with Google, Facebook and other OAUTH services
