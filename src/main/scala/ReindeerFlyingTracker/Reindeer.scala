/** Scala book chapter 4 - **/ 

package ReindeerFlyingTracker

/**Case classes*/
case class HasRedNose(value: Boolean)
case class CanFlyMagically (value: Boolean)
case class FlightDistance (value: Double) //Km
case class FlightSpeed(value: Double) //Km per hour

/**Immutable blueprint base Reindeer class **/
class Reindeer(val name: String, 
               val age: Int,
               val canFlyMagically: CanFlyMagically) {

  def aboutReindeer(): String = 
    s"Hi!! I'm $name, a $age yr old reindeer!. + " + 
    s"Do you want to know if I can can fly magically? $canFlyMagically"
}

/**Companion Object - serves as a factory **/
object Reindeer {
  def createReindeer(name: String, 
                     age: Int,
                     canFlyMagically: CanFlyMagically) : Reindeer = {
    new Reindeer(name, age, canFlyMagically)
  }
}


