(ns starter.browser
  (:require [shadow.cljs.modern :refer-macros [js-await]]))

;; start is called by init and after code reloading finishes
(defn ^:dev/after-load start []
  (js/console.log "start"))

(defn init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (js/console.log "init")
  (start))

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))



(defn my-parse-fn [res]
  (js-await [body (.json res)]
            (js/console.log "got some json" body)))

(defn my-fetch-fn []
  (js-await [res (js/fetch "/some.json")]
            (js/console.log "res" res)
            (js-await [parsed (my-parse-fn res)]
                      (js/console.log parsed))))

(js/console.log "my-fetch-fn" (my-fetch-fn))