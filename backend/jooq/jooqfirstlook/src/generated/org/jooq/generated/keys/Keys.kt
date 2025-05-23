/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.keys


import org.jooq.ForeignKey
import org.jooq.UniqueKey
import org.jooq.generated.tables.JActor
import org.jooq.generated.tables.JAddress
import org.jooq.generated.tables.JCategory
import org.jooq.generated.tables.JCity
import org.jooq.generated.tables.JCountry
import org.jooq.generated.tables.JCustomer
import org.jooq.generated.tables.JFilm
import org.jooq.generated.tables.JFilmActor
import org.jooq.generated.tables.JFilmCategory
import org.jooq.generated.tables.JFilmText
import org.jooq.generated.tables.JInventory
import org.jooq.generated.tables.JLanguage
import org.jooq.generated.tables.JPayment
import org.jooq.generated.tables.JRental
import org.jooq.generated.tables.JStaff
import org.jooq.generated.tables.JStore
import org.jooq.generated.tables.records.ActorRecord
import org.jooq.generated.tables.records.AddressRecord
import org.jooq.generated.tables.records.CategoryRecord
import org.jooq.generated.tables.records.CityRecord
import org.jooq.generated.tables.records.CountryRecord
import org.jooq.generated.tables.records.CustomerRecord
import org.jooq.generated.tables.records.FilmActorRecord
import org.jooq.generated.tables.records.FilmCategoryRecord
import org.jooq.generated.tables.records.FilmRecord
import org.jooq.generated.tables.records.FilmTextRecord
import org.jooq.generated.tables.records.InventoryRecord
import org.jooq.generated.tables.records.LanguageRecord
import org.jooq.generated.tables.records.PaymentRecord
import org.jooq.generated.tables.records.RentalRecord
import org.jooq.generated.tables.records.StaffRecord
import org.jooq.generated.tables.records.StoreRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal



// -------------------------------------------------------------------------
// UNIQUE and PRIMARY KEY definitions
// -------------------------------------------------------------------------

val KEY_ACTOR_PRIMARY: UniqueKey<ActorRecord> = Internal.createUniqueKey(JActor.ACTOR, DSL.name("KEY_actor_PRIMARY"), arrayOf(JActor.ACTOR.ACTOR_ID), true)
val KEY_ADDRESS_PRIMARY: UniqueKey<AddressRecord> = Internal.createUniqueKey(JAddress.ADDRESS, DSL.name("KEY_address_PRIMARY"), arrayOf(JAddress.ADDRESS.ADDRESS_ID), true)
val KEY_CATEGORY_PRIMARY: UniqueKey<CategoryRecord> = Internal.createUniqueKey(JCategory.CATEGORY, DSL.name("KEY_category_PRIMARY"), arrayOf(JCategory.CATEGORY.CATEGORY_ID), true)
val KEY_CITY_PRIMARY: UniqueKey<CityRecord> = Internal.createUniqueKey(JCity.CITY, DSL.name("KEY_city_PRIMARY"), arrayOf(JCity.CITY.CITY_ID), true)
val KEY_COUNTRY_PRIMARY: UniqueKey<CountryRecord> = Internal.createUniqueKey(JCountry.COUNTRY, DSL.name("KEY_country_PRIMARY"), arrayOf(JCountry.COUNTRY.COUNTRY_ID), true)
val KEY_CUSTOMER_PRIMARY: UniqueKey<CustomerRecord> = Internal.createUniqueKey(JCustomer.CUSTOMER, DSL.name("KEY_customer_PRIMARY"), arrayOf(JCustomer.CUSTOMER.CUSTOMER_ID), true)
val KEY_FILM_PRIMARY: UniqueKey<FilmRecord> = Internal.createUniqueKey(JFilm.FILM, DSL.name("KEY_film_PRIMARY"), arrayOf(JFilm.FILM.FILM_ID), true)
val KEY_FILM_ACTOR_PRIMARY: UniqueKey<FilmActorRecord> = Internal.createUniqueKey(JFilmActor.FILM_ACTOR, DSL.name("KEY_film_actor_PRIMARY"), arrayOf(JFilmActor.FILM_ACTOR.ACTOR_ID, JFilmActor.FILM_ACTOR.FILM_ID), true)
val KEY_FILM_CATEGORY_PRIMARY: UniqueKey<FilmCategoryRecord> = Internal.createUniqueKey(JFilmCategory.FILM_CATEGORY, DSL.name("KEY_film_category_PRIMARY"), arrayOf(JFilmCategory.FILM_CATEGORY.FILM_ID, JFilmCategory.FILM_CATEGORY.CATEGORY_ID), true)
val KEY_FILM_TEXT_PRIMARY: UniqueKey<FilmTextRecord> = Internal.createUniqueKey(JFilmText.FILM_TEXT, DSL.name("KEY_film_text_PRIMARY"), arrayOf(JFilmText.FILM_TEXT.FILM_ID), true)
val KEY_INVENTORY_PRIMARY: UniqueKey<InventoryRecord> = Internal.createUniqueKey(JInventory.INVENTORY, DSL.name("KEY_inventory_PRIMARY"), arrayOf(JInventory.INVENTORY.INVENTORY_ID), true)
val KEY_LANGUAGE_PRIMARY: UniqueKey<LanguageRecord> = Internal.createUniqueKey(JLanguage.LANGUAGE, DSL.name("KEY_language_PRIMARY"), arrayOf(JLanguage.LANGUAGE.LANGUAGE_ID), true)
val KEY_PAYMENT_PRIMARY: UniqueKey<PaymentRecord> = Internal.createUniqueKey(JPayment.PAYMENT, DSL.name("KEY_payment_PRIMARY"), arrayOf(JPayment.PAYMENT.PAYMENT_ID), true)
val KEY_RENTAL_PRIMARY: UniqueKey<RentalRecord> = Internal.createUniqueKey(JRental.RENTAL, DSL.name("KEY_rental_PRIMARY"), arrayOf(JRental.RENTAL.RENTAL_ID), true)
val KEY_RENTAL_RENTAL_DATE: UniqueKey<RentalRecord> = Internal.createUniqueKey(JRental.RENTAL, DSL.name("KEY_rental_rental_date"), arrayOf(JRental.RENTAL.RENTAL_DATE, JRental.RENTAL.INVENTORY_ID, JRental.RENTAL.CUSTOMER_ID), true)
val KEY_STAFF_PRIMARY: UniqueKey<StaffRecord> = Internal.createUniqueKey(JStaff.STAFF, DSL.name("KEY_staff_PRIMARY"), arrayOf(JStaff.STAFF.STAFF_ID), true)
val KEY_STORE_IDX_UNIQUE_MANAGER: UniqueKey<StoreRecord> = Internal.createUniqueKey(JStore.STORE, DSL.name("KEY_store_idx_unique_manager"), arrayOf(JStore.STORE.MANAGER_STAFF_ID), true)
val KEY_STORE_PRIMARY: UniqueKey<StoreRecord> = Internal.createUniqueKey(JStore.STORE, DSL.name("KEY_store_PRIMARY"), arrayOf(JStore.STORE.STORE_ID), true)

// -------------------------------------------------------------------------
// FOREIGN KEY definitions
// -------------------------------------------------------------------------

val FK_ADDRESS_CITY: ForeignKey<AddressRecord, CityRecord> = Internal.createForeignKey(JAddress.ADDRESS, DSL.name("fk_address_city"), arrayOf(JAddress.ADDRESS.CITY_ID), org.jooq.generated.keys.KEY_CITY_PRIMARY, arrayOf(JCity.CITY.CITY_ID), true)
val FK_CITY_COUNTRY: ForeignKey<CityRecord, CountryRecord> = Internal.createForeignKey(JCity.CITY, DSL.name("fk_city_country"), arrayOf(JCity.CITY.COUNTRY_ID), org.jooq.generated.keys.KEY_COUNTRY_PRIMARY, arrayOf(JCountry.COUNTRY.COUNTRY_ID), true)
val FK_CUSTOMER_ADDRESS: ForeignKey<CustomerRecord, AddressRecord> = Internal.createForeignKey(JCustomer.CUSTOMER, DSL.name("fk_customer_address"), arrayOf(JCustomer.CUSTOMER.ADDRESS_ID), org.jooq.generated.keys.KEY_ADDRESS_PRIMARY, arrayOf(JAddress.ADDRESS.ADDRESS_ID), true)
val FK_CUSTOMER_STORE: ForeignKey<CustomerRecord, StoreRecord> = Internal.createForeignKey(JCustomer.CUSTOMER, DSL.name("fk_customer_store"), arrayOf(JCustomer.CUSTOMER.STORE_ID), org.jooq.generated.keys.KEY_STORE_PRIMARY, arrayOf(JStore.STORE.STORE_ID), true)
val FK_FILM_LANGUAGE: ForeignKey<FilmRecord, LanguageRecord> = Internal.createForeignKey(JFilm.FILM, DSL.name("fk_film_language"), arrayOf(JFilm.FILM.LANGUAGE_ID), org.jooq.generated.keys.KEY_LANGUAGE_PRIMARY, arrayOf(JLanguage.LANGUAGE.LANGUAGE_ID), true)
val FK_FILM_LANGUAGE_ORIGINAL: ForeignKey<FilmRecord, LanguageRecord> = Internal.createForeignKey(JFilm.FILM, DSL.name("fk_film_language_original"), arrayOf(JFilm.FILM.ORIGINAL_LANGUAGE_ID), org.jooq.generated.keys.KEY_LANGUAGE_PRIMARY, arrayOf(JLanguage.LANGUAGE.LANGUAGE_ID), true)
val FK_FILM_ACTOR_ACTOR: ForeignKey<FilmActorRecord, ActorRecord> = Internal.createForeignKey(JFilmActor.FILM_ACTOR, DSL.name("fk_film_actor_actor"), arrayOf(JFilmActor.FILM_ACTOR.ACTOR_ID), org.jooq.generated.keys.KEY_ACTOR_PRIMARY, arrayOf(JActor.ACTOR.ACTOR_ID), true)
val FK_FILM_ACTOR_FILM: ForeignKey<FilmActorRecord, FilmRecord> = Internal.createForeignKey(JFilmActor.FILM_ACTOR, DSL.name("fk_film_actor_film"), arrayOf(JFilmActor.FILM_ACTOR.FILM_ID), org.jooq.generated.keys.KEY_FILM_PRIMARY, arrayOf(JFilm.FILM.FILM_ID), true)
val FK_FILM_CATEGORY_CATEGORY: ForeignKey<FilmCategoryRecord, CategoryRecord> = Internal.createForeignKey(JFilmCategory.FILM_CATEGORY, DSL.name("fk_film_category_category"), arrayOf(JFilmCategory.FILM_CATEGORY.CATEGORY_ID), org.jooq.generated.keys.KEY_CATEGORY_PRIMARY, arrayOf(JCategory.CATEGORY.CATEGORY_ID), true)
val FK_FILM_CATEGORY_FILM: ForeignKey<FilmCategoryRecord, FilmRecord> = Internal.createForeignKey(JFilmCategory.FILM_CATEGORY, DSL.name("fk_film_category_film"), arrayOf(JFilmCategory.FILM_CATEGORY.FILM_ID), org.jooq.generated.keys.KEY_FILM_PRIMARY, arrayOf(JFilm.FILM.FILM_ID), true)
val FK_INVENTORY_FILM: ForeignKey<InventoryRecord, FilmRecord> = Internal.createForeignKey(JInventory.INVENTORY, DSL.name("fk_inventory_film"), arrayOf(JInventory.INVENTORY.FILM_ID), org.jooq.generated.keys.KEY_FILM_PRIMARY, arrayOf(JFilm.FILM.FILM_ID), true)
val FK_INVENTORY_STORE: ForeignKey<InventoryRecord, StoreRecord> = Internal.createForeignKey(JInventory.INVENTORY, DSL.name("fk_inventory_store"), arrayOf(JInventory.INVENTORY.STORE_ID), org.jooq.generated.keys.KEY_STORE_PRIMARY, arrayOf(JStore.STORE.STORE_ID), true)
val FK_PAYMENT_CUSTOMER: ForeignKey<PaymentRecord, CustomerRecord> = Internal.createForeignKey(JPayment.PAYMENT, DSL.name("fk_payment_customer"), arrayOf(JPayment.PAYMENT.CUSTOMER_ID), org.jooq.generated.keys.KEY_CUSTOMER_PRIMARY, arrayOf(JCustomer.CUSTOMER.CUSTOMER_ID), true)
val FK_PAYMENT_RENTAL: ForeignKey<PaymentRecord, RentalRecord> = Internal.createForeignKey(JPayment.PAYMENT, DSL.name("fk_payment_rental"), arrayOf(JPayment.PAYMENT.RENTAL_ID), org.jooq.generated.keys.KEY_RENTAL_PRIMARY, arrayOf(JRental.RENTAL.RENTAL_ID), true)
val FK_PAYMENT_STAFF: ForeignKey<PaymentRecord, StaffRecord> = Internal.createForeignKey(JPayment.PAYMENT, DSL.name("fk_payment_staff"), arrayOf(JPayment.PAYMENT.STAFF_ID), org.jooq.generated.keys.KEY_STAFF_PRIMARY, arrayOf(JStaff.STAFF.STAFF_ID), true)
val FK_RENTAL_CUSTOMER: ForeignKey<RentalRecord, CustomerRecord> = Internal.createForeignKey(JRental.RENTAL, DSL.name("fk_rental_customer"), arrayOf(JRental.RENTAL.CUSTOMER_ID), org.jooq.generated.keys.KEY_CUSTOMER_PRIMARY, arrayOf(JCustomer.CUSTOMER.CUSTOMER_ID), true)
val FK_RENTAL_INVENTORY: ForeignKey<RentalRecord, InventoryRecord> = Internal.createForeignKey(JRental.RENTAL, DSL.name("fk_rental_inventory"), arrayOf(JRental.RENTAL.INVENTORY_ID), org.jooq.generated.keys.KEY_INVENTORY_PRIMARY, arrayOf(JInventory.INVENTORY.INVENTORY_ID), true)
val FK_RENTAL_STAFF: ForeignKey<RentalRecord, StaffRecord> = Internal.createForeignKey(JRental.RENTAL, DSL.name("fk_rental_staff"), arrayOf(JRental.RENTAL.STAFF_ID), org.jooq.generated.keys.KEY_STAFF_PRIMARY, arrayOf(JStaff.STAFF.STAFF_ID), true)
val FK_STAFF_ADDRESS: ForeignKey<StaffRecord, AddressRecord> = Internal.createForeignKey(JStaff.STAFF, DSL.name("fk_staff_address"), arrayOf(JStaff.STAFF.ADDRESS_ID), org.jooq.generated.keys.KEY_ADDRESS_PRIMARY, arrayOf(JAddress.ADDRESS.ADDRESS_ID), true)
val FK_STORE_ADDRESS: ForeignKey<StoreRecord, AddressRecord> = Internal.createForeignKey(JStore.STORE, DSL.name("fk_store_address"), arrayOf(JStore.STORE.ADDRESS_ID), org.jooq.generated.keys.KEY_ADDRESS_PRIMARY, arrayOf(JAddress.ADDRESS.ADDRESS_ID), true)
val FK_STORE_STAFF: ForeignKey<StoreRecord, StaffRecord> = Internal.createForeignKey(JStore.STORE, DSL.name("fk_store_staff"), arrayOf(JStore.STORE.MANAGER_STAFF_ID), org.jooq.generated.keys.KEY_STAFF_PRIMARY, arrayOf(JStaff.STAFF.STAFF_ID), true)
