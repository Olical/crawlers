(ns crawlers.fetcher
  (:require [cheshire.core :as cheshire]))

(def source-url "https://raw.githubusercontent.com/monperrus/crawler-user-agents/master/crawler-user-agents.json")

(defn -main []
  (-> source-url
      (slurp)
      (cheshire/parse-string true)
      (->> (mapv :pattern))
      (prn)))
