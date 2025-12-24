/** Scala book chapter 4
 Incorporating: 1.Case class, 2.Trait, 3.Private val, 4.Companion object,
 5. Inheritance (extends with) 6. Methods with side effects  7. App trait extends App
 **/

package ReindeerFlyingTracker

case class HasRedNose(value: Boolean)
case class CanFlyMagically (value: Boolean)
case class FlightDistance (value: Double) //Km
case class FlightSpeed(value: Double) //Km per hour
case class HoursFlown(value: Int) //Total hours flown
case class FitnessLevel(value: Int) //1-10

trait MagicalFlyer {
  def canFlyMagically: Boolean  //trait method

  def takeOff(): String = {
    if (canFlyMagically) "Taking off into the Christmas night sky tonight!" //Trait uses this method that returns boolean
    else "Sorry no flying for me, I must stay at the North Pole tonight."
  }
}

/** [BASE CLASS] Immutable blueprint Reindeer class **/
class Reindeer(val name: String,
               val age: Int,
               private val canFlyMagically: CanFlyMagically) { //Private field, noone outside of this class can access it

  /** Public method that reads the private data canFlyMagically (encapsulation) */
  def canFly: Boolean = canFlyMagically.value

  def aboutReindeer(): String =
    s"Hi!! I'm $name, a $age yr old reindeer! " +
    s"Can I fly magically? $canFly!!"
} //base class closure


/**[COMPANION OBJ for BASE CLASS] for Reindeer - serves as a factory **/
object Reindeer {
  def createReindeer(name: String,
                     age: Int,
                     magicalAbility: CanFlyMagically) : Reindeer = {
    new Reindeer(name, age, magicalAbility)
  }
} //companion object closure

  /** [JUNIOR REINDEER] */
class JuniorReindeer (name: String,
                      age: Int,
                      magicalFlightAbility: CanFlyMagically,
                      val healthCheck: Int, // 0-100%, diff % required depending on type of reindeer
                      private val fitnessLevel: FitnessLevel) //Private field
  extends Reindeer (name, age, magicalFlightAbility) with MagicalFlyer {


    // Override trait method [needed by trait MagicalFlyer]
    override def canFlyMagically: Boolean = canFly //must be in class not comp obj

    ///** Who will fly tonight? - Use private field through its public method */
    //def isFitToFly: Boolean = {
      //healthCheck >= 70 && getFitness >= 7 && canFly
    //Add pattern matching here for diff categories of reindeers


    // Public method to read private encapsulation

    // Check who can fly tonight

}


  // Companion object for JuniorReindeer
  object JuniorReindeer {
    def createJuniorReindeer(name: String,
                             age: Int,
                             magicalFlightAbility: CanFlyMagically,
                             healthCheck: Int,
                             fitnessLevel: FitnessLevel): JuniorReindeer = {
      new JuniorReindeer(name, age, magicalFlightAbility, healthCheck, fitnessLevel)
    }
  }

//Add a singleton object with mutable state

  object JuniorReindeerTrackerApp extends App { //App outside of everything else
    // Your code runs here!
  }