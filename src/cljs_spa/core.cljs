(ns cljs-spa.core
  (:require [reagent.core :as r]
            [cljs-spa.util :as util]
            [cljs-spa.router :as myrouter]
            [cljs-spa.routes :as routes]))

(defonce re-init-router
  (util/singleton-fn (fn [] (myrouter/create-router routes/the-routes))
                     myrouter/stop-router))

(defn re-render [router]
  (r/render [myrouter/router-ui
             {:router router, :render-fn (fn [props] [routes/switch-ui props])}]
            (.getElementById js/document "app")))

(defn re-init []
  (let [router (re-init-router)]
    (.start router (fn [err] (if err (throw err) (re-render router))))))

(re-init)
#_(myrouter/create-router routes/the-routes)
