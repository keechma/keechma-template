(ns {{ns-name}}.core 
  (:require [reagent.core :as reagent]
            [keechma.app-state :as app-state]
            [keechma.toolbox.dataloader.app :as dataloader]
            [keechma.toolbox.forms.app :as forms]
            [{{ns-name}}.controllers :refer [controllers]]
            [{{ns-name}}.ui :refer [ui]]
            [{{ns-name}}.subscriptions :refer [subscriptions]]
            [{{ns-name}}.edb :refer [edb-schema]]
            [{{ns-name}}.datasources :refer [datasources]]
            [{{ns-name}}.forms :as {{ns-name}}-forms]))

(def app-definition
  (-> {:components    ui
       :controllers   controllers
       :subscriptions subscriptions
       :html-element  (.getElementById js/document "app")}
      (dataloader/install datasources edb-schema)
      (forms/install {{ns-name}}-forms/forms {{ns-name}}-forms/forms-automount-fns)))

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
