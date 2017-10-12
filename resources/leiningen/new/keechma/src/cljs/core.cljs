(ns {{ns-name}}.core 
  (:require [reagent.core :as reagent]
            [keechma.app-state :as app-state]
            [{{ns-name}}.controllers :refer [controllers]]
            [{{ns-name}}.ui :refer [ui]]
            [{{ns-name}}.subscriptions :refer [subscriptions]]))

(def app-definition
  {:components    ui
   :controllers   controllers
   :subscriptions subscriptions
   :html-element  (.getElementById js/document "app")})

(defonce running-app (clojure.core/atom nil))

(defn start-app! []
  (reset! running-app (app-state/start! app-definition)))

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")))

(defn reload []
  (let [current @running-app]
    (if current
      (app-state/stop! current start-app!)
      (start-app!))))

(defn ^:export main []
  (dev-setup)
  (start-app!))
