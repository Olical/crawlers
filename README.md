# Crawlers [![Clojars Project](https://img.shields.io/clojars/v/olical/crawlers.svg)](https://clojars.org/olical/crawlers)

Clojure(Script) library to identify crawler and bot user agent strings. Relies on [monperrus/crawler-user-agents][crawler-list-repo] for the regular expressions.

## Usage

```clojure
(require '[crawlers.detector :as crawlers])

;; This isn't a crawler.
(time (crawlers/crawler? "Mozilla/5.0 (X11; Linux x86_64; rv:62.0) Gecko/20100101 Firefox/62.0"))
;; "Elapsed time: 0.214012 msecs"
;; => nil

;; This is!
(time (crawlers/crawler? "Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko; compatible; Googlebot/2.1; +http://www.google.com/bot.html) Safari/537.36"))
;; "Elapsed time: 0.056963 msecs"
;; => "Googlebot/"
```

## Updating

The list of expressions is fetched from [`crawler-user-agents.json`][json-source] so it needs to be occasionally synchronised.

You can submit an update to the regular expression list by executing `clj -Afetch` and pasting the resulting vector in `src/crawlers/detector.cljc`.

The last update was performed at `Tue 23 Oct 11:46:14 BST 2018`. Pull requests to update this are welcome.

## Unlicenced

Find the full [unlicense][] in the `UNLICENSE` file, but here's a snippet.

>This is free and unencumbered software released into the public domain.
>
>Anyone is free to copy, modify, publish, use, compile, sell, or distribute this software, either in source code form or as a compiled binary, for any purpose, commercial or non-commercial, and by any means.

Do what you want. Learn as much as you can. Unlicense more software.

[unlicense]: http://unlicense.org/
[crawler-list-repo]: https://github.com/monperrus/crawler-user-agents
[json-source]: https://raw.githubusercontent.com/monperrus/crawler-user-agents/master/crawler-user-agents.json
