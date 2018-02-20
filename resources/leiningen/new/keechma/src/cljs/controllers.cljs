(ns {{ns-name}}.controllers
  (:require [keechma.toolbox.dataloader.controller :as dataloader-controller]
            [{{ns-name}}.controllers.counter :as counter]
            [{{ns-name}}.edb :refer [edb-schema]]
            [{{ns-name}}.datasources :refer [datasources]]))

(def controllers
  (-> {:counter counter/controller}
      (dataloader-controller/register datasources edb-schema)))
