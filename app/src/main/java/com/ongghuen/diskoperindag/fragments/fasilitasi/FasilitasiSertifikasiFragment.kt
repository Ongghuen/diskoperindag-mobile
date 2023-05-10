package com.ongghuen.diskoperindag.fragments.fasilitasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ongghuen.diskoperindag.R
import com.ongghuen.diskoperindag.databinding.FragmentFasilitasiSertifikasiBinding

class FasilitasiSertifikasiFragment : Fragment() {

    private var _binding: FragmentFasilitasiSertifikasiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFasilitasiSertifikasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}