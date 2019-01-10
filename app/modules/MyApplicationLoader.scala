package modules

import chat.ChatEngine
import play.api._
import com.softwaremill.macwire._
import _root_.controllers.AssetsComponents
import akka.cluster.Cluster
import akka.management.AkkaManagement
import akka.management.cluster.bootstrap.ClusterBootstrap
import play.engineio.EngineIOController
import play.socketio.scaladsl.SocketIOComponents

class MyApplicationLoader extends ApplicationLoader {
  override def load(context: ApplicationLoader.Context) =
    new BuiltInComponentsFromContext(context) with MyApplication {

      LoggerConfigurator.apply(context.environment.classLoader)
        .foreach(_.configure(context.environment))

      context.environment.mode match {

        case Mode.Prod =>
          // Start Akka management and bootstrap the cluster
          AkkaManagement(actorSystem).start()
          ClusterBootstrap(actorSystem).start()

        case Mode.Dev =>
          // In dev mode, we just have a single node, so we will just form a cluster by ourselves
          val cluster = Cluster(actorSystem)
          cluster.join(cluster.selfAddress)

        case _ => sys.error("Don't know how to load the application for tests")
      }

    }.application
}

trait MyApplication extends BuiltInComponents
  with AssetsComponents
  with SocketIOComponents {

  lazy val chatEngine = wire[ChatEngine]
  lazy val engineIOController: EngineIOController = chatEngine.controller

  override lazy val router = {
    val prefix = "/"
    wire[_root_.router.Routes]
  }
  override lazy val httpFilters = Nil
}