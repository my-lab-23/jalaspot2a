package controllers

import scala.language.postfixOps

import javax.inject._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

import my_spotify.MySpotify

//case class LabelForm(label: String)

/*
object LabelForm {
   val form: Form[LabelForm] = Form(mapping("label" -> text)
   (LabelForm.apply)
   (LabelForm.unapply))
}
*/

@Singleton
class OtherController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {

   def otherForm() = Action { implicit request: Request[AnyContent] =>
      Ok(views.html.otherForm(LabelForm.form))
   }

   def pre() = {
      MySpotify.currentAlbums()
      MySpotify.extractIds(".temp")
      MySpotify.removeAlbums(".temp")
   }

   def otherFormPost() = Action { implicit request =>
      val formData: LabelForm = LabelForm.form.bindFromRequest.get
      val label = formData.label
      this.pre()
      println(label)
      MySpotify.saveAlbums(label)
      Redirect("https://open.spotify.com/collection/albums")
   }
}
