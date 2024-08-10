package com.example.mindtech

object DataProvider {

    private val cars = listOf(
        ListItems( "Tesla Model S", "Electric Luxury Sedan"),
        ListItems( "Ford Mustang", "American Muscle Car"),
        ListItems("BMW M3", "High-Performance Sports Car"),
        ListItems( "Chevrolet Corvette", "Iconic American Sports Car"),
        ListItems("Porsche 911", "Legendary German Sports Car"),
        ListItems( "Lamborghini Aventador", "Italian Supercar"),
        ListItems("Ferrari F8 Tributo", "High-Performance Italian Car"),
        ListItems( "Audi R8", "German Supercar with V10 Engine"),
        ListItems( "Mercedes-Benz S-Class", "Luxury Full-Size Sedan"),
        ListItems( "Jaguar F-Type", "British Sports Car")
    )

    private val rivers = listOf(
        ListItems( "Amazon River", "Second longest river in the world"),
        ListItems( "Nile River", "Longest river in the world"),
        ListItems( "Yangtze River", "Longest river in Asia"),
        ListItems( "Mississippi River", "Major river in the United States"),
        ListItems( "Ganges River", "Sacred River in India"),
        ListItems( "Danube River", "Europe's Second-Longest River"),
        ListItems( "Volga River", "The Longest River in Europe"),
        ListItems( "Mekong River", "Major River in Southeast Asia"),
        ListItems( "Congo River", "The Deepest River in the World"),
        ListItems( "Thames River", "Famous River in London")
    )

    private val fruits = listOf(
        ListItems( "Apple", "Crisp and juicy"),
        ListItems( "Banana", "Rich in potassium"),
        ListItems( "Orange", "High in Vitamin C"),
        ListItems( "Strawberry", "Sweet and tangy"),
        ListItems( "Grapes", "Small, Sweet or Tart Berries"),
        ListItems( "Pineapple", "Tropical Fruit with a Crown of Leaves"),
        ListItems( "Mango", "Juicy Stone Fruit"),
        ListItems( "Blueberry", "Small and Nutritious Blue Berries"),
        ListItems( "Watermelon", "Large Fruit with Red, Juicy Flesh"),
        ListItems( "Kiwi", "Small Fruit with Brown Skin and Green Flesh")
    )

    private val forests = listOf(
        ListItems( "Amazon Rainforest", "Largest tropical rainforest"),
        ListItems( "Black Forest", "Forest in Germany, known for its cuckoo clocks"),
        ListItems( "Redwood Forest", "Home to the tallest trees on earth"),
        ListItems( "Sherwood Forest", "Famous for the legend of Robin Hood"),
        ListItems( "Daintree Rainforest", "A Tropical Rainforest in Australia"),
        ListItems( "Tongass National Forest", "The Largest National Forest in the US"),
        ListItems( "Taiga Forest", "The Largest Terrestrial Biome"),
        ListItems( "Monteverde Cloud Forest", "A Unique Cloud Forest in Costa Rica"),
        ListItems( "Congo Rainforest", "The World's Second Largest Rainforest"),
        ListItems( "Borneo Rainforest", "Ancient Rainforest in Southeast Asia")
    )

    private val cities = listOf(
        ListItems( "New York City", "The city that never sleeps"),
        ListItems( "Paris", "The city of lights"),
        ListItems("Tokyo", "A bustling metropolis in Japan"),
        ListItems("Sydney", "Famous for its Opera House"),
        ListItems( "Dubai", "Known for Modern Architecture"),
        ListItems("Singapore", "A Global Financial Hub"),
        ListItems( "Rome", "The Capital of Italy"),
        ListItems( "Berlin", "The Capital of Germany"),
        ListItems("Istanbul", "A City Connecting Europe and Asia")
    )

    // Function to get data by category
    fun getDataByCategory(category: Category): List<ListItems> {
        return when (category) {
            Category.Cars -> cars
            Category.Rivers -> rivers
            Category.Fruits -> fruits
            Category.Forests -> forests
            Category.Cities -> cities
        }
    }
}