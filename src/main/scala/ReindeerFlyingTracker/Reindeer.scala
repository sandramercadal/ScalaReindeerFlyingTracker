/** Scala book chapter 4
 Incorporating: 1.Case class, 2.Trait, 3.Private val, 4.Companion object, 5. Inheritance (extends with) 6.  7. 
 **/

package ReindeerFlyingTracker

case class HasRedNose(value: Boolean)
case class CanFlyMagically (value: Boolean)
case class FlightDistance (value: Double) //Km
case class FlightSpeed(value: Double) //Km per hour
case class HoursFlown(value: Int) //Total hours flown
case class FitnessLevel(value: Int) //1-10

trait MagicalFlyer {
  def canFlyMagically: Boolean

  def takeOff(): String = {
    if (canFlyMagically) "Taking off into the Christmas night sky tonight!"
    else "Sorry no flying for me, I must stay at the North Pole tonight."
  }
}

/**Immutable blueprint base Reindeer class **/
class Reindeer(val name: String,
               val age: Int,
               private val canFlyMagically: CanFlyMagically) { //Private field, noone outside of this class can access it

  /** Public method that reads the private data canFlyMagically (encapsulation) */
  def canFly: Boolean = canFlyMagically.value

  def aboutReindeer(): String =
    s"Hi!! I'm $name, a $age yr old reindeer!." +
    s"Can I fly magically? $canFlyMagically"
}

/**Companion Object for Reindeer - serves as a factory **/
object Reindeer {
  def createReindeer(name: String,
                     age: Int,
                     canFlyMagically: CanFlyMagically) : Reindeer = {
    new Reindeer(name, age, canFlyMagically)
  }
  
  
  
  
  
  
  
}


class JuniorReindeer (name: String,
                      age: Int,
                      canFlyMagically: CanFlyMagically,
                      val healthCheck: Int, // 0-100%, needs to be at >70% to fly 
                      private val fitnessLevel: FitnessLevel)
  extends Reindeer (name, age, canFlyMagically) with MagicalFlyer {

  // Companion object for JuniorReindeer
  object JuniorReindeer {
    def createJuniorReindeer(name: String,
                             age: Int,
                             canFlyMagically: CanFlyMagically,
                             healthCheck: Int,
                             fitnessLevel: FitnessLevel): JuniorReindeer = {
      new JuniorReindeer(name, age, canFlyMagically, healthCheck, fitnessLevel)
    }
  }

  object JuniorReindeerTrackerApp extends App {
    // Your code runs here!
  }