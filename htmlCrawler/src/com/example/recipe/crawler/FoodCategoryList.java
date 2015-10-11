package com.example.recipe.crawler;


/**
 * Created by root on 26/9/15.
 */
public class FoodCategoryList {
	
	enum FoodCategory{
        NORTH_INDIAN("NorthIndian"),
        SOUTH_INDIAN("SouthIndian"),
        VEGETARIAN("Vegetarian"),
        NON_VEGETARIAN("NonVegetarian"),

        EASY("Easy"),

        //Sweet's
        MILKSHAKES("MilkShakes"),
        CAKES("Cakes"),
        DESSERTS("Desserts"),
        KIDS("Kids"),
        DRINKS("Drinks"),
        BEVERAGE("beverage"),

        TANDOOR("Tandoor"),

        CHINESE("Chinese"),
        SOUP("Soup"),
        CHUTNEY("Chutney"),
        SANDWHICH("sandwich"),
        CURRY("Curry"),

        SNACKS("Snacks"),
        SAUCE("sauce"),
        THAI("Thai"),
        FRENCH("French"),
        ITALIAN("Italian"),
        SALAD("Salad"),
        PASTA("Pasta"),
        PARATHA("Paratha"),
        BAKED("BAKED"),
        HEALTHY("HEALTHY"),
        
        // food items
        CHICKEN("Chicken"),
        LAMB("Lamb"),
        MUTTON("Mutton"),
        EGG("Egg"),
        PANEER("Paneer"),
        PORK("Pork"),
        BEEF("Beef"),
        FISH("Fish"),
        PRAWN("Prawn"),

        BREAKFAST("Breakfast"),
        LUNCH("Lunch"),
        DINNER("Dinner"),

        //Carbohydrate
        RICE("Rice"),

        // location Based
        RAJASTHANI("rajasthani"),
        PUNJABI("punjabi"),
        GUJRATI("gujrati"),
        BENGALI("Bengali"),
        KERALA("KERALA"),
        
        //Hidden Tags
        DEFAULT("default");
        
        String val;
        
        FoodCategory(String str) {
        	val = str.toLowerCase();
        };
        
        String getValue() {return val;}
	}
}
