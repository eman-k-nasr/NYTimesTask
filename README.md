# NYTimesTask
[A simple App that fetches popular articles(most viewed) from **https://api.nytimes.com/**]
# First of all you have to enter your api_key from your account on **https://api.nytimes.com/**
###### You can navigate to *utils package/utils.kt/*
and insert your api-key (very important to run your application)
###### *const val API_KEY = " "* 
# Architecture Pattern That I used :
#### MVVM 
### Design pattern that I used
##### ServiceLocator Design Pattern: to allow me swap the real repository with a fake one while testing 
# Technologies:
- Coroutines
- Retrofit
- Navigation Component
# Testing:
#### I applied testing for each of : viewmodels,repository,fragments 
- Junit 
- Test Doubles [Fake]
- Coroutines Test
- Espresso and Mockito
#### NYTimesTask contains 2 main screens
- ArticlesScreen (which displays a list of most viewed articles from nytimes api)
- DetailsScreen (which displays the details of the selected article from the list)

<img src="https://user-images.githubusercontent.com/59161258/112725790-9ec06100-8f22-11eb-82d2-6355679e1b8f.jpg" width="300" height="600">              <img src="https://user-images.githubusercontent.com/59161258/112725804-b0a20400-8f22-11eb-995f-dfb12e54cce9.jpg" width="300" height="600">


