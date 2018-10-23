(ns crawlers.detector
  "Detects bots and crawlers by their user agent string.")

(def fetched-re-strs
  "Generated by `clj -Afetch` from https://github.com/monperrus/crawler-user-agents."
  ["Googlebot\\/" "Googlebot-Mobile" "Googlebot-Image" "Googlebot-News" "Googlebot-Video"
   "AdsBot-Google([^-]|$)" "AdsBot-Google-Mobile" "Feedfetcher-Google" "Mediapartners-Google"
   "Mediapartners \\(Googlebot\\)" "APIs-Google" "bingbot" "Slurp" "[wW]get" "curl" "LinkedInBot"
   "Python-urllib" "python-requests" "libwww" "httpunit" "nutch" "Go-http-client" "phpcrawl"
   "msnbot" "jyxobot" "FAST-WebCrawler" "FAST Enterprise Crawler" "BIGLOTRON" "Teoma" "convera"
   "seekbot" "Gigabot" "Gigablast" "exabot" "ia_archiver" "GingerCrawler" "webmon " "HTTrack"
   "grub.org" "UsineNouvelleCrawler" "antibot" "netresearchserver" "speedy" "fluffy" "bibnum.bnf"
   "findlink" "msrbot" "panscient" "yacybot" "AISearchBot" "ips-agent" "tagoobot" "MJ12bot"
   "woriobot" "yanga" "buzzbot" "mlbot" "YandexBot" "yandex.com\\/bots" "purebot" "Linguee Bot"
   "CyberPatrol" "voilabot" "Baiduspider" "citeseerxbot" "spbot" "twengabot" "postrank" "turnitinbot"
   "scribdbot" "page2rss" "sitebot" "linkdex" "Adidxbot" "blekkobot" "ezooms" "dotbot"
   "Mail.RU_Bot" "discobot" "heritrix" "findthatfile" "europarchive.org" "NerdByNature.Bot"
   "sistrix crawler" "Ahrefs(Bot|SiteAudit)" "fuelbot" "CrunchBot" "centurybot9" "IndeedBot"
   "mappydata" "woobot" "ZoominfoBot" "PrivacyAwareBot" "Multiviewbot" "SWIMGBot" "Grobbot"
   "eright" "Apercite" "semanticbot" "Aboundex" "domaincrawler" "wbsearchbot" "summify" "CCBot"
   "edisterbot" "seznambot" "ec2linkfinder" "gslfbot" "aiHitBot" "intelium_bot"
   "facebookexternalhit" "Yeti" "RetrevoPageAnalyzer" "lb-spider" "Sogou" "lssbot" "careerbot"
   "wotbox" "wocbot" "ichiro" "DuckDuckBot" "lssrocketcrawler" "drupact" "webcompanycrawler"
   "acoonbot" "openindexspider" "gnam gnam spider" "web-archive-net.com.bot" "backlinkcrawler"
   "coccoc" "integromedb" "content crawler spider" "toplistbot" "it2media-domain-crawler"
   "ip-web-crawler.com" "siteexplorer.info" "elisabot" "proximic" "changedetection" "arabot"
   "WeSEE:Search" "niki-bot" "CrystalSemanticsBot" "rogerbot" "360Spider" "psbot" "InterfaxScanBot"
   "CC Metadata Scaper" "g00g1e.net" "GrapeshotCrawler" "urlappendbot" "brainobot" "fr-crawler"
   "binlar" "SimpleCrawler" "Twitterbot" "cXensebot" "smtbot" "bnf.fr_bot" "A6-Indexer" "ADmantX"
   "Facebot" "OrangeBot\\/" "memorybot" "AdvBot" "MegaIndex" "SemanticScholarBot" "ltx71" "nerdybot"
   "xovibot" "BUbiNG" "Qwantify" "archive.org_bot" "Applebot" "TweetmemeBot" "crawler4j" "findxbot"
   "S[eE][mM]rushBot" "yoozBot" "lipperhey" "Y!J" "Domain Re-Animator Bot" "AddThis"
   "Screaming Frog SEO Spider" "MetaURI" "Scrapy" "Livelap[bB]ot" "OpenHoseBot" "CapsuleChecker"
   "collection@infegy.com" "IstellaBot" "DeuSu\\/" "betaBot" "Cliqzbot\\/" "MojeekBot\\/"
   "netEstate NE Crawler" "SafeSearch microdata crawler" "Gluten Free Crawler\\/" "Sonic"
   "Sysomos" "Trove" "deadlinkchecker" "Slack-ImgProxy" "Embedly" "RankActiveLinkBot" "iskanie"
   "SafeDNSBot" "SkypeUriPreview" "Veoozbot" "Slackbot" "redditbot" "datagnionbot"
   "Google-Adwords-Instant" "adbeat_bot" "WhatsApp" "contxbot" "pinterest" "electricmonk"
   "GarlikCrawler" "BingPreview\\/" "vebidoobot" "FemtosearchBot" "Yahoo Link Preview" "MetaJobBot"
   "DomainStatsBot" "mindUpBot" "Daum\\/" "Jugendschutzprogramm-Crawler" "Xenu Link Sleuth"
   "Pcore-HTTP" "moatbot" "KosmioBot" "pingdom" "PhantomJS" "Gowikibot" "PiplBot" "Discordbot"
   "TelegramBot" "Jetslide" "newsharecounts" "James BOT" "Barkrowler" "TinEye" "SocialRankIOBot"
   "trendictionbot" "Ocarinabot" "epicbot" "Primalbot" "DuckDuckGo-Favicons-Bot" "GnowitNewsbot"
   "Leikibot" "LinkArchiver" "YaK\\/" "PaperLiBot" "Digg Deeper" "dcrawl" "Snacktory"
   "AndersPinkBot" "Fyrebot" "EveryoneSocialBot" "Mediatoolkitbot" "Luminator-robots" "ExtLinksBot"
   "SurveyBot" "NING\\/" "okhttp" "Nuzzel" "omgili" "PocketParser" "YisouSpider" "um-LN"
   "ToutiaoSpider" "MuckRack" "Jamie's Spider" "AHC\\/" "NetcraftSurveyAgent" "Laserlikebot"
   "Apache-HttpClient" "AppEngine-Google" "Jetty" "Upflow" "Thinklab" "Traackr.com" "Twurly"
   "Mastodon" "http_get" "DnyzBot" "botify" "007ac9 Crawler" "BehloolBot" "BrandVerity"
   "check_http" "BDCbot" "ZumBot" "EZID" "ICC-Crawler" "ArchiveBot" "^LCC "
   "filterdb.iss.net\\/crawler" "BLP_bbot" "BomboraBot" "Buck\\/" "Companybook-Crawler" "Genieo"
   "magpie-crawler" "MeltwaterNews" "Moreover" "newspaper\\/" "ScoutJet" "(^| )sentry\\/"
   "StorygizeBot" "UptimeRobot" "OutclicksBot" "seoscanners" "Hatena" "Google Web Preview"
   "MauiBot" "AlphaBot" "SBL-BOT" "IAS crawler" "adscanner" "Netvibes" "acapbot" "Baidu-YunGuanCe"
   "bitlybot" "blogmuraBot" "Bot.AraTurka.com" "bot-pge.chlooe.com" "BoxcarBot" "BTWebClient"
   "ContextAd Bot" "Digincore bot" "Disqus" "Feedly" "Fetch\\/" "Fever" "Flamingo_SearchEngine"
   "FlipboardProxy" "g2reader-bot" "imrbot" "K7MLWCBot" "Kemvibot" "Landau-Media-Spider"
   "linkapediabot" "vkShare" "Siteimprove.com" "BLEXBot\\/" "DareBoost" "ZuperlistBot\\/"
   "Miniflux\\/" "Feedspotbot\\/" "Diffbot\\/" "SEOkicks" "tracemyfile" "Nimbostratus-Bot" "zgrab"
   "PR-CY.RU" "AdsTxtCrawler" "Datafeedwatch" "Zabbix" "TangibleeBot" "google-xrawler" "axios"
   "Amazon CloudFront" "Pulsepoint" "CloudFlare-AlwaysOnline" "Google-Structured-Data-Testing-Tool"
   "WordupInfoSearch" "WebDataStats" "HttpUrlConnection" "Seekport Crawler" "ZoomBot"
   "VelenPublicWebCrawler" "MoodleBot" "jpg-newsbot" "outbrain" "W3C_Validator" "Validator\\.nu"
   "W3C-checklink" "W3C-mobileOK" "W3C_I18n-Checker" "FeedValidator" "W3C_CSS_Validator" "W3C_Unicorn"])
(def crawler?
  (apply some-fn (map #(partial re-find (re-pattern %)) fetched-re-strs)))

(comment
  ;; This isn't a crawler.
  (time (crawler? "Mozilla/5.0 (X11; Linux x86_64; rv:62.0) Gecko/20100101 Firefox/62.0"))
  ;; "Elapsed time: 0.214012 msecs"
  ;; => nil

  ;; This is!
  (time (crawler? "Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko; compatible; Googlebot/2.1; +http://www.google.com/bot.html) Safari/537.36"))
  ;; "Elapsed time: 0.056963 msecs"
  ;; => "Googlebot/"
  )
