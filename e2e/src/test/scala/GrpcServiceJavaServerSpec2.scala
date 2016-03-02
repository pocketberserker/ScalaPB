
import com.trueaccord.proto.e2e.{Service1Grpc => Service1GrpcJava, _}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Random

class GrpcServiceJavaServerSpec2 extends GrpcServiceSpecBase {

  describe("java server") {

    it("unaryStringLength blockingStub") {
      withJavaServer { channel =>
        val client = Service1GrpcJava.newBlockingStub(channel)
        val string = randomString()
        val request = Service.Req1.newBuilder.setRequest(string).build()
        client.unaryStringLength(request).getLength must be(string.length)
      }
    }

    it("unaryStringLength stub") {
      withJavaServer { channel =>
        val client = Service1GrpcJava.newFutureStub(channel)
        val string = randomString()
        val request = Service.Req1.newBuilder.setRequest(string).build()
        client.unaryStringLength(request).get().getLength must be(string.length)
      }
    }

    it("clientStreamingCount") {
      withJavaServer { channel =>
        val client = Service1GrpcJava.newStub(channel)
        val (responseObserver, future) = getObserverAndFuture[Service.Res2]
        val requestObserver = client.clientStreamingCount(responseObserver)
        val n = Random.nextInt(10)
        for (_ <- 1 to n) {
          val request = Service.Req2.newBuilder.build()
          requestObserver.onNext(request)
        }

        requestObserver.onCompleted()
        Await.result(future, 2.seconds).getCount must be(n)
      }
    }

    it("serverStreamingFan") {
      withJavaServer { channel =>
        val client = Service1GrpcJava.newStub(channel)
        val (observer, future) = getObserverAndFutureVector[Service.Res3]
        val request = Service.Req3.newBuilder.setNum(100).build()
        client.serverStreamingFan(request, observer)
        val response = Service.Res3.newBuilder.build()
        Await.result(future, 2.seconds) must be(Vector.fill(100)(response))
      }
    }

    it("bidiStreamingDoubler") {
      withJavaServer { channel =>
        val client = Service1GrpcJava.newStub(channel)
        val (responseObserver, future) = getObserverAndFutureVector[Service.Res4]
        val requestObserver = client.bidiStreamingDoubler(responseObserver)
        val request0 = Service.Req4.newBuilder.setA(11).build()
        requestObserver.onNext(request0)
        val request1 = Service.Req4.newBuilder.setA(3).build()
        requestObserver.onNext(request1)
        val request2 = Service.Req4.newBuilder.setA(6).build()
        requestObserver.onNext(request2)
        requestObserver.onCompleted()
        Await.result(future, 2.seconds).map(_.getB) must be(Vector(22, 6, 12))
      }
    }
  }

}
