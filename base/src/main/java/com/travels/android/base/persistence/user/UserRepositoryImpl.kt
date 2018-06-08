package com.travels.android.base.persistence.user

import io.reactivex.Single

class UserRepositoryImpl(val userDAO: UserDAO) : UserRepository {

    override fun saveUser(user: User): Single<Long> {
        return Single.just(userDAO.saveUser(UserModel(null, user.userId, username = user.username)))
    }

    override fun getUser(userId: Long): Single<User> {
        return Single.just(userDAO.getUser(userId)).map { userModel -> User(userModel.uid!!, userModel.username!!) }
    }
}