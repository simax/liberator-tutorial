(ns liberator-tutorial.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [defroutes ANY]]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))

(defresource parameter [txt]
  :available-media-types ["text/plain"]
  :handle-ok (fn [_] (format "The text is:-:-: %s" txt)))

(defroutes main-routes
  (ANY "/bar/:txt" [txt] (parameter txt)))


(def app
  (-> (handler/site main-routes)))

