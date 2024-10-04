// Крісло
interface Chair {
    fun sitOn(): String
}

// Диван
interface Sofa {
    fun lieOn(): String
}

// Столик
interface CoffeeTable {
    fun placeItem(): String
}

// Крісло в стилі Модерн
class ModernChair : Chair {
    override fun sitOn(): String = "Sitting on a modern chair"
}

// Диван в стилі Модерн
class ModernSofa : Sofa {
    override fun lieOn(): String = "Lying on a modern sofa"
}

// Столик в стилі Модерн
class ModernCoffeeTable : CoffeeTable {
    override fun placeItem(): String = "Placing an item on a modern coffee table"
}

// Крісло в вікторіанському стилі
class VictorianChair : Chair {
    override fun sitOn(): String = "Sitting on a Victorian chair"
}

// Диван в вікторіанському стилі
class VictorianSofa : Sofa {
    override fun lieOn(): String = "Lying on a Victorian sofa"
}

// Столик в вікторіанському стилі
class VictorianCoffeeTable : CoffeeTable {
    override fun placeItem(): String = "Placing an item on a Victorian coffee table"
}

// Формування Фабрик
interface FurnitureFactory {
    fun createChair(): Chair
    fun createSofa(): Sofa
    fun createCoffeeTable(): CoffeeTable
}

class ModernFurnitureFactory : FurnitureFactory {
    override fun createChair(): Chair = ModernChair()
    override fun createSofa(): Sofa = ModernSofa()
    override fun createCoffeeTable(): CoffeeTable = ModernCoffeeTable()
}

class VictorianFurnitureFactory : FurnitureFactory {
    override fun createChair(): Chair = VictorianChair()
    override fun createSofa(): Sofa = VictorianSofa()
    override fun createCoffeeTable(): CoffeeTable = VictorianCoffeeTable()
}

class FurnitureShop(private val factory: FurnitureFactory) {
    fun furnishRoom() {
        val chair = factory.createChair()
        val sofa = factory.createSofa()
        val coffeeTable = factory.createCoffeeTable()

        println(chair.sitOn())
        println(sofa.lieOn())
        println(coffeeTable.placeItem())
    }
}

fun main() {
    val modernShop = FurnitureShop(ModernFurnitureFactory())
    modernShop.furnishRoom()

    val victorianShop = FurnitureShop(VictorianFurnitureFactory())
    victorianShop.furnishRoom()
}
