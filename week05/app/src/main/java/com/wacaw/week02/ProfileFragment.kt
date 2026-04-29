package com.wacaw.week02

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wacaw.week02.data.remote.ApiClient
import com.wacaw.week02.data.remote.response.UserResponse
import com.wacaw.week02.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.bumptech.glide.Glide
import com.wacaw.week02.adapter.FollowersAdapter


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var followersAdapter: FollowersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followersAdapter = FollowersAdapter(emptyList())
        binding.llFollower.adapter = followersAdapter

        ApiClient.userService.getUser().enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    val userResponse = response.body()

                    // 상단 프로필
                    val firstUser = userResponse?.data?.get(0)
                    firstUser?.let {
                        binding.tvNickname.text = "${it.first_name} ${it.last_name}"
                        Glide.with(this@ProfileFragment)
                            .load(it.avatar)
                            .circleCrop()
                            .into(binding.ivAvatar)
                    }

                    // 리사이클러뷰, 전체 유저 리스트
                    userResponse?.data?.let { userList ->

                        val followingList = userList.drop(1)

                        followersAdapter.setList(followingList)

                        // 팔로잉 숫자
                        binding.tvFollowerCount.text = "팔로잉 (${followingList.size})"
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("API_ERROR", t.message ?: "Unknown Error")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}