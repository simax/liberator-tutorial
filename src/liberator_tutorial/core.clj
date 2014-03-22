(ns liberator-tutorial.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [defroutes ANY]]))

(defresource parameter [txt]
  :available-media-types ["text/plain"]
  :handle-ok (fn [_] (format "The text is:-:-: %s" txt)))

(defroutes app
  (ANY "/bar/:txt" [txt] (parameter txt)))


(def handler
  (-> app
    (wrap-params)))

(run-jetty #'handler {:port 3000})
