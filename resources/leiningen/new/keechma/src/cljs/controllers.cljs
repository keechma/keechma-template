(ns {{ns-name}}.controllers
  (:require
            [{{ns-name}}.controllers.counter :as counter]
           
      ))

(def controllers
  (-> {:counter counter/controller}))
