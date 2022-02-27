User Stories
As a user, I want to be able to create my own custom foods.
As a user, I want to be able to create my own custom items.
As a user, I want to be able to create my own custom recipes.
As a user, I want to be able to add both my own, and other users' foods to my recipes.
As a user, I want to be able to set personal macro/calorie goals on a daily basis.
As a user, I want to be able to add foods/recipes to a daily food log.
As a user, I want to be able to edit/delete my custom foods.
As a user, I want to be able to edit/delete my custom items.
As a user, I want to be able to edit/delete my custom recipes.
Difficulties
The most difficult part of this project was creating the database in such a way that users were able to edit their own foods/items/recipes
while still being able to view, include, and use other users' throughout the site. This was accomplished via a variety of
table relationships which revolve around the user entity primarily. All tables tie back to the user in one way or another
via foreign key relationships.
The above solution also created additional difficulties in ensuring data was not duplicated in queries as entities are
heavily tied together (user_id for example being in all tables). To work around this, I utilized a variety of subqueries
to return the data appropriately.