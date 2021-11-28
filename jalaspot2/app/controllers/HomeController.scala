package controllers

import javax.inject._
import play.api.mvc._

import sys.process._
import scala.language.postfixOps
import my_spotify.MySpotify
import my_dropbox.MyDropbox
import my_utility.MyUtility

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
   def index() = Action { implicit request: Request[AnyContent] =>
      Ok(views.html.index())
   }

   def pre() = {
      MySpotify.currentAlbums()
      MySpotify.extractIds(".temp")
      MySpotify.removeAlbums(".temp")
   }

   def haydn() = Action { implicit request: Request[AnyContent] =>
      this.pre()
      MySpotify.saveAlbums("haydn")
      Redirect("https://open.spotify.com/collection/albums")
   }

   def mozart() = Action { implicit request: Request[AnyContent] =>
      this.pre()
      MySpotify.saveAlbums("mozart")
      Redirect("https://open.spotify.com/collection/albums")
   }

   def invernizzi() = Action { implicit request: Request[AnyContent] =>
      this.pre()
      MySpotify.saveAlbums("invernizzi")
      Redirect("https://open.spotify.com/collection/albums")
   }

   def faust() = Action { implicit request: Request[AnyContent] =>
      this.pre()
      MySpotify.saveAlbums("faust")
      Redirect("https://open.spotify.com/collection/albums")
   }

   def haydn2032() = Action { implicit request: Request[AnyContent] =>
      this.pre()
      MySpotify.saveAlbums("haydn2032")
      Redirect("https://open.spotify.com/collection/albums")
   }

   //

   def remove() = {
      MySpotify.currentAlbums()
      MySpotify.extractIds(".temp")
      MySpotify.removeAlbums(".temp")
   }

   def remove_web() = Action { implicit request: Request[AnyContent] =>
      this.remove()
      Redirect("https://open.spotify.com/collection/albums")
   }

   def remove_android() = Action { implicit request: Request[AnyContent] =>
      this.remove()
      Ok("Removed!")
   }

   //

   def dropbox() = Action { implicit request: Request[AnyContent] =>
      val client = MyDropbox.create_dropbox_client()

      val dir = sys.env("MY_DATA_PATH_SPOTIFY")
      val file = "spotify.tar.gz"
      val exit_code = s"tar -czvf $file $dir" !

      if(exit_code == 0) {
         MyDropbox.upload_file(file, client)
      }
      Redirect("/")
   }

   //

   def load(label: String) = Action { implicit request: Request[AnyContent] =>
      this.pre()
      MySpotify.saveAlbums(label)
      Ok("Loaded!")
   }

   def save(label: String) = Action { implicit request: Request[AnyContent] =>
      MySpotify.currentAlbums()
      MySpotify.extractIds(label)      
      Ok("Saved!")
   }

   def saved() = Action { implicit request: Request[AnyContent] =>
      val ls = MyUtility.ls()
      Ok(ls)
   }
}
