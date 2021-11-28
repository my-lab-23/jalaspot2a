package controllers

import scala.language.postfixOps

import javax.inject._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

import my_spotify.MySpotify

case class LabelForm(label: String)

object LabelForm {
   val form: Form[LabelForm] = Form(mapping("label" -> text)
   (LabelForm.apply)
   (LabelForm.unapply))
}

@Singleton
class SaveController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {

   def saveForm() = Action { implicit request: Request[AnyContent] =>
      Ok(views.html.saveForm(LabelForm.form))
   }

   def saveFormPost() = Action { implicit request =>
      val formData: LabelForm = LabelForm.form.bindFromRequest.get
      val label = formData.label
      MySpotify.currentAlbums()
      MySpotify.extractIds(label)
      Redirect("/")
   }
}
