import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

// サンプルのシナリオのため、URLやパラメータは適当な値を指定しています
class testScenario extends Simulation {
  
  // httpの設定
  val httpProtocolConf = http
    .baseUrl("https://example.jp") // ベースURL
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // リクエストヘッダー
    .basicAuth("xxxxxxxxxx", "yyyyyyyyyy") // Basic認証
    .doNotTrackHeader("1") // DNTヘッダー
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0") // ユーザーエージェント

  // シナリオの記述
  val scn = scenario("シナリオA")
            // 1つ目に実行されるAPI
            .exec(
              http("API-1") // リクエスト名称
              .post("/xxxxx/auth") // POSTメソッドとURIの指定
              .formParam("loginId", "1a2b3c4d5e") // リクエストパラメータの指定
              .formParam("loginPassword", "abcdefg12345") // リクエストパラメータの指定
              .check(status.is(200)) // ステータスのチェック
              .check(jsonPath("$.contents.accessToken").saveAs("accessToken")) // レスポンスのBodyの取得
            )
            // デバッグとして、取得したアクセストークンを出力
            .exec(session => {
                 val accessTokenParam = session("accessToken").asOption[String]
                 println("accessToken："+ accessTokenParam)
                 session
            })
            // 1秒スリープ
            .pause(1)
            // 2つ目に実行されるAPI
            .exec(
              http("API-2")
              .post("/xxxxx/get/userData")
              .formParam("accessToken", "${accessToken}")
              .check(status.is(200))
            )
  
  // 実行
  setUp(
 	// ユーザー数や実行期間を設定
    scn.inject(
      constantUsersPerSec(5) during(1800 seconds)
    ).protocols(httpProtocolConf)
  )
}
