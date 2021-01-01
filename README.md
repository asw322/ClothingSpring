# ClothingSpring 

Version 1.2 of ClothingSpring

# Abstract
The Clothing Software is an advanced outfit matching software based on a given collection of clothings provided by the user, the clothing software recommends one outfit in the morning based on:


1. The Weather
2. Your calednar events
3. Your favorite influencer(s) 
The software has a Tinder-like interface that allows the user to state whether they "like", "dislike", or "favorite" the given outfit. The software will then adjust the model based on the user's responses, similar to how other software updates the user content based on several factors. 

# Functionalities
1. Smart clothing picker
2. Reduces the stress of having to find a good pair of outfit

# Inspirations
How can I dress well but still be as minimalistic as Steve Jobs? 

# How does it work? 

Note: We are describing the final product, not first iterations
1. Setup Phase
    create account (basic user information)
    setup user clothing choices based on the user's wardrobe

2. Learning Phase
    Using machine learning, neural networks, supervised, linear regeression training
    Note: We are inputting the user's clothing on a pretrained model

3. User Interaction Phase: 
    system recommends one clothing outfit in the morning based on: 
    Weather 
    Your events on your calendar
    Your favorite influencers 



# Sources
Trello Board: https://trello.com/b/4G4Upgg2


# Ideas
1. Balances out the wear numbers (trying to prioritize a even wear and tear)
2. Figure out which colors go well together (based on a color, there are websites for that)


# How to connect to demodb and see all tables

<!-- Check which docker containers are open -->
<!-- May need to start postgres docker container if closed -->
> docker ps
> docker exec -it <container_id> bin/bash 

<!-- Connect with postgres through docker -->
> psql -U postgres

<!-- Enter the demodb database -->
> \c demodb 
> \dt


# Test Account
username: test
password: test
